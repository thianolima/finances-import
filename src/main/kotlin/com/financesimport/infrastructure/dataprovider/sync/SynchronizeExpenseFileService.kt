package com.financesimport.infrastructure.dataprovider.sync

import com.financesimport.core.gateway.SynchronizeExpenseFile
import com.financesimport.core.model.ExpenseFile
import com.financesimport.core.model.ExpenseFileItem
import com.financesimport.core.model.StatusExpenseEnum
import com.financesimport.infrastructure.dataprovider.client.FinanceClient
import com.financesimport.infrastructure.dataprovider.client.response.FinanceResponse
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class SynchronizeExpenseFileService (
    val financeClient: FinanceClient
) : SynchronizeExpenseFile {

    override fun execute(expenseFile: ExpenseFile): ExpenseFile =
        expenseFile.items.map{
            syncPaymentData(it)
                .run {
                    when(this.isPayment()){
                        true -> {
                            log.info("found payment this expense id ${this.idfinance}")
                            this
                        }
                        false -> {
                            log.info("search last category with this describe: ${this.description}")
                            this
                        }
                    }
                }
        }.let {
            ExpenseFile(it, expenseFile.objectKey)
        }

    override fun syncPaymentData(item: ExpenseFileItem): ExpenseFileItem =
        try{
            financeClient.getExpenseByBuyDateAndAmout(
                item.amount,
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(item.buyDate)
            ).let {
                updateItem(item, it)
            }
        } catch(ex : FeignException){
            log.info("item not found with amount ${item.amount} and ${item.buyDate} ")
            updateItem(item, null)
        }

    private fun updateItem(item: ExpenseFileItem, dto : FinanceResponse?) =
        ExpenseFileItem(
            buyDate = dto?.buyDate ?: item.buyDate,
            description = dto?.description ?: item.description,
            amount = dto?.amount ?: item.amount,
            status = StatusExpenseEnum.SINCRONIZADO,
            idfinance = dto?.id
        )

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}