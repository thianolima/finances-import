package com.financesimport.core.model

import java.util.*

data class ExpenseFile (
    val id: String,
    val objectKey: String,
    val items : List<ExpenseFileItem>
){
    constructor(items: List<ExpenseFileItem>, objectKey: String) : this(
        id = UUID.randomUUID().toString(),
        objectKey = objectKey,
        items = items
    )
}