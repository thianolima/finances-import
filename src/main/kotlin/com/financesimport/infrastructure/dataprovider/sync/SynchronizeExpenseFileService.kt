package com.financesimport.infrastructure.dataprovider.sync

import com.financesimport.core.gateway.SynchronizeExpenseFile
import com.financesimport.core.model.ExpenseFile
import com.financesimport.core.model.ExpenseFileItem
import com.financesimport.core.model.StatusExpenseEnum
import com.financesimport.infrastructure.dataprovider.client.FinanceClient
import com.financesimport.infrastructure.dataprovider.client.response.CategoryResponse
import com.financesimport.infrastructure.dataprovider.client.response.ExpenseResponse
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
                            log.info("found payment this expense id ${this.idexpense}")
                            this
                        }
                        false -> {
                            log.info("search last category with this describe: ${this.description}")
                            syncCategory(this)
                        }
                    }
                }
        }.let {
            ExpenseFile(it, expenseFile.objectKey)
        }

    override fun syncPaymentData(item: ExpenseFileItem): ExpenseFileItem =
        try{
            financeClient.findExpenseByBuyDateAndAmout(
                item.amount,
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(item.buyDate)
            ).let {
                updateItem(item, it, it.category)
            }
        } catch(ex : FeignException){
            log.info("item not found with amount ${item.amount} and ${item.buyDate} ")
            updateItem(item, null, null)
        }

    override fun syncCategory(item: ExpenseFileItem): ExpenseFileItem {
        try{
            financeClient.findLastCategoryByExpenseDescription(
                item.description
            ).let {
                return updateItem(item, null, it)
            }
        } catch(ex : FeignException){
            log.info("item not found with expense description  ${item.description}")
            return updateItem(item, null, null)
        }
    }

    private fun updateItem(item: ExpenseFileItem, expenseResponse : ExpenseResponse?, categoryResponse: CategoryResponse?) =
        ExpenseFileItem(
            buyDate = expenseResponse?.buyDate ?: item.buyDate,
            description = expenseResponse?.description ?: item.description,
            amount = expenseResponse?.amount ?: item.amount,
            status = StatusExpenseEnum.SINCRONIZADO,
            idexpense = expenseResponse?.id,
            idcategory = categoryResponse?.id ?: "0",
            category = categoryResponse?.name ?: "SEM_CATEGORIA"
        )

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}