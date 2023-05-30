package com.financesimport.infrastructure.dataprovider.database.mapper

import com.financesimport.core.model.ExpenseFileItem
import com.financesimport.infrastructure.dataprovider.database.entity.ExpenseFileItemEntity

fun ExpenseFileItem.toEntity() =
    ExpenseFileItemEntity(
        description = this.description,
        buyDate = this.buyDate,
        amount = this.amount
    )