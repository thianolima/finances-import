package com.financesimport.core.gateway

import com.financesimport.core.model.ExpenseFile
import com.financesimport.core.model.ExpenseFileItem

interface SynchronizeExpenseFile {
    fun execute(expenseFile : ExpenseFile) : ExpenseFile
    fun syncPaymentData(item: ExpenseFileItem): ExpenseFileItem
}