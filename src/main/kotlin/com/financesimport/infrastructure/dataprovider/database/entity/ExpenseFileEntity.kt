package com.financesimport.infrastructure.dataprovider.database.entity

import com.financesimport.core.model.ExpenseFileItem
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "expense_file")
@DynamicUpdate
data class ExpenseFileEntity (
    @Id
    val id: String,
    var objectKey: String
//    @OneToMany
//    val items : List<ExpenseFileItem>? = null
)