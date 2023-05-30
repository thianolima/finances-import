package com.financesimport.core.gateway

import com.financesimport.core.model.ExpenseFile

interface SaveExpenseFile {

    fun execute(expenseFile : ExpenseFile)
}