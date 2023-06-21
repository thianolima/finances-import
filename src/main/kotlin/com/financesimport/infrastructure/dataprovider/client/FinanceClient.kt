package com.financesimport.infrastructure.dataprovider.client

import com.financesimport.infrastructure.dataprovider.client.response.CategoryResponse
import com.financesimport.infrastructure.dataprovider.client.response.ExpenseResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "finance", url = "http://localhost:8080")
interface FinanceClient {

    @GetMapping("/expenses/search")
    fun findExpenseByBuyDateAndAmout(@RequestParam amount : Double, @RequestParam buyDate : String) : ExpenseResponse

    @GetMapping("/expenses/lastcategory")
    fun findLastCategoryByExpenseDescription(@RequestParam description : String) : CategoryResponse
}