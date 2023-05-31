package com.financesimport.infrastructure.dataprovider.database.repository

import com.financesimport.infrastructure.dataprovider.database.entity.ExpenseFileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseFileRepository:JpaRepository<ExpenseFileEntity, String>{

}