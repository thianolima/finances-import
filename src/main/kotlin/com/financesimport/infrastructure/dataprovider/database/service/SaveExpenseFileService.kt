package com.financesimport.infrastructure.dataprovider.database.service

import com.financesimport.core.gateway.SaveExpenseFile
import com.financesimport.core.model.ExpenseFile
import com.financesimport.core.model.ExpenseFileItem
import com.financesimport.core.model.StatusExpenseEnum
import com.financesimport.infrastructure.dataprovider.database.mapper.toEntity
import com.financesimport.infrastructure.dataprovider.database.repository.ExpenseFileRepository
import com.financesimport.infrastructure.dataprovider.feign.FinanceClient
import com.financesimport.infrastructure.dataprovider.feign.FinanceDto
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@Service
class SaveExpenseFileService (
    val repository : ExpenseFileRepository,
    val financeClient: FinanceClient
) : SaveExpenseFile {

    @Transactional
    override fun execute(expenseFile: ExpenseFile){
        val expenseFileSinchonized = sinchronized(expenseFile)
        expenseFileSinchonized.objectKey = expenseFile.objectKey
        repository.save(expenseFileSinchonized.toEntity())
    }

    fun sinchronized(expenseFile: ExpenseFile) =
        expenseFile.items.map{
            try {
                val dto = financeClient.getExpenseByBuyDateAndAmout(
                    it.amount,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd").format(it.buyDate)
                )
                updateExpenseFileItem(it, true, dto)
            } catch (e: FeignException) {
                when(e.status()){
                    404 -> log.info(e.message)
                    else -> log.error(e.message)
                }
                updateExpenseFileItem(it, false, null)
            }
        }.let {
            ExpenseFile(it)
        }

    private fun updateExpenseFileItem(expenseFileItem: ExpenseFileItem, payment: Boolean, dto : FinanceDto?) =
        ExpenseFileItem(
            buyDate = expenseFileItem.buyDate,
            description = expenseFileItem.description,
            amount = expenseFileItem.amount,
            status = StatusExpenseEnum.ATUALIZADO,
            payment = payment,
            idfinance = dto?.id
        )

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}