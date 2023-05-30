package com.financesimport.infrastructure.dataprovider.database.entity

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "expense_file_item")
@DynamicUpdate
data class ExpenseFileItemEntity (
    @Id
    val id: String,
    val description: String,
    var dueDate: LocalDate? = null,
    val buyDate: LocalDate,
    var payDate: LocalDate? = null,
    val pay: Boolean = false,
    val amount: Double
)