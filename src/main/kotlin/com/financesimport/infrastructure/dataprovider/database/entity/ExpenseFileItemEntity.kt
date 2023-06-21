package com.financesimport.infrastructure.dataprovider.database.entity

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import javax.persistence.*

@Embeddable
@Table(name = "expense_file_item")
@DynamicUpdate
data class ExpenseFileItemEntity (
    val description: String,
    val buyDate: LocalDate,
    val amount: Double,
    var idexpense : String? = null,
    var idcategory : String? = null,
    var category: String? = null
)