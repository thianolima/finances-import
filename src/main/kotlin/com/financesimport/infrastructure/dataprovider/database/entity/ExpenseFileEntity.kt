package com.financesimport.infrastructure.dataprovider.database.entity

import com.financesimport.core.model.ExpenseFileItem
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "expense_file")
@DynamicUpdate
data class ExpenseFileEntity (
    @Id
    val id: String,
    var objectKey: String,
    @ElementCollection
    @CollectionTable(name = "expense_file_item", joinColumns = [JoinColumn(name = "id_expense_file")])
    val items : List<ExpenseFileItemEntity>
)