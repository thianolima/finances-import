package com.financesimport.core.model

import java.time.LocalDate

data  class ExpenseFileItem (
    val buyDate: LocalDate,
    val description: String,
    val amount: Double,
    var status : StatusExpenseEnum = StatusExpenseEnum.IMPORTADO,
    var idfinance : String? = null
){
    fun isPayment() : Boolean = !idfinance.isNullOrEmpty()
}
