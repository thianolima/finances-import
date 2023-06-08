package com.financesimport.core.model

import java.time.LocalDate

data  class ExpenseFileItem (
    val buyDate: LocalDate,
    val description: String,
    val amount: Double,
    var status : StatusExpenseEnum = StatusExpenseEnum.IMPORTADO,
    var payment : Boolean = false,
    var idfinance : String? = null
)