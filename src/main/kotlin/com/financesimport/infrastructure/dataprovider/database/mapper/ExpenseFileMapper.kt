package com.financesimport.infrastructure.dataprovider.database.mapper

import com.financesimport.core.model.ExpenseFile
import com.financesimport.infrastructure.dataprovider.database.entity.ExpenseFileEntity

fun ExpenseFile.toEntity() =
    ExpenseFileEntity(
        id = this.id,
        objectKey = this.objectKey!!,
        items = this.items.map { it.toEntity() }
    )
