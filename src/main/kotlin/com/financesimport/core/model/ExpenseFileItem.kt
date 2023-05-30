package com.financesimport.core.model

import java.time.LocalDate
import java.util.*

data  class ExpenseFileItem (
    val id: String,
    val description: String,
    var dueDate: LocalDate? = null,
    val buyDate: LocalDate,
    var payDate: LocalDate? = null,
    val pay: Boolean = false,
    val amount: Double,
    var objectKey: String? = null
    ){
    constructor(buyDate: LocalDate, description: String, amount: Double) : this(
        id = UUID.randomUUID().toString(),
        amount = amount,
        buyDate = buyDate,
        description = description
    )
}