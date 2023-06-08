package com.financesimport.infrastructure.dataprovider.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "finance", url = "http://localhost:8080")
interface FinanceClient {

    @GetMapping("/expenses/search")
    fun getExpenseByBuyDateAndAmout(@RequestParam amount: Double, @RequestParam buyDate : String) : FinanceDto
}