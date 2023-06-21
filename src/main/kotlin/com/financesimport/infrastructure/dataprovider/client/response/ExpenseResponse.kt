package com.financesimport.infrastructure.dataprovider.client.response

import java.time.LocalDate

data class ExpenseResponse (
    val id: String,
    val description: String,
    val buyDate: LocalDate,
    val amount: Double,
    val category : CategoryResponse
)