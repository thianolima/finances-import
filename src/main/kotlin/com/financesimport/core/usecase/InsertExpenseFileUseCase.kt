package com.financesimport.core.usecase

import com.financesimport.core.gateway.SaveExpenseFile
import com.financesimport.core.model.ExpenseFile

class InsertExpenseFileUseCase(
    val saveExpenseFile : SaveExpenseFile
) {
    fun execute(expenseFile: ExpenseFile){
        saveExpenseFile.execute(expenseFile)
    }
}