package com.financesimport.core.model

import java.time.LocalDate

data  class ExpenseFileItem (
    val buyDate: LocalDate,
    val description: String,
    val amount: Double,
    var status : StatusExpenseEnum = StatusExpenseEnum.IMPORTADO,
    var idexpense : String? = null,
    var idcategory : String? = null,
    var category: String? = null
){
    fun isPayment() : Boolean = !idexpense.isNullOrEmpty()
}
