package com.financesimport.core.gateway

import com.financesimport.core.model.ExpenseFile
import java.io.InputStream

interface ConvertExcelFileToExpenseFile {
    fun execute(fileXLS:InputStream, objectKey: String):ExpenseFile
}