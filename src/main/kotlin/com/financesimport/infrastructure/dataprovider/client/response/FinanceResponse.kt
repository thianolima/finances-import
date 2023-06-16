package com.financesimport.infrastructure.dataprovider.client.response

import java.time.LocalDate

data class FinanceResponse (
    val id: String,
    val description: String,
    val buyDate: LocalDate,
    val amount: Double
)