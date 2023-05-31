package com.financesimport.infrastructure.dataprovider.database.service

import com.financesimport.core.gateway.SaveExpenseFile
import com.financesimport.core.model.ExpenseFile
import com.financesimport.infrastructure.dataprovider.database.mapper.toEntity
import com.financesimport.infrastructure.dataprovider.database.repository.ExpenseFileRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SaveExpenseFileService (
    val repository : ExpenseFileRepository
) : SaveExpenseFile {

    @Transactional
    override fun execute(expenseFile: ExpenseFile){
        repository.save(expenseFile.toEntity())
    }
}