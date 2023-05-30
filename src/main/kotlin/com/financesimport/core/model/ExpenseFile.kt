package com.financesimport.core.model

import java.util.*

data class ExpenseFile (
    val id: String,
    var objectKey: String? = null,
    val items : List<ExpenseFileItem>
){
    constructor(items: List<ExpenseFileItem>) : this(
        id = UUID.randomUUID().toString(),
        items = items
    )
}