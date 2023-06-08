package com.financesimport.infrastructure.dataprovider.feign

import java.time.LocalDate

data class FinanceDto (
    val id: String,
    val description: String,
    val buyDate: LocalDate,
    val amount: Double
)