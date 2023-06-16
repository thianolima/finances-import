package com.financesimport.core.gateway

import com.financesimport.core.model.ExpenseFile

interface SynchronizeExpenseFile {
    fun execute(expenseFile : ExpenseFile) : ExpenseFile
}