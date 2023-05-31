package com.financesimport.core.usecase

import com.financesimport.core.gateway.ConvertExcelFileToExpenseFile
import com.financesimport.core.gateway.GetObjectStorage
import com.financesimport.core.gateway.SaveExpenseFile

class ImportExpenseFileUseCase(
    val saveExpenseFile : SaveExpenseFile,
    val convertExcelFileToExpenseFile: ConvertExcelFileToExpenseFile,
    val getObjectStorage : GetObjectStorage,
) {
    fun execute(bucketName : String, objectKey : String){
        val fileXLS = getObjectStorage.execute(bucketName, objectKey)
        val expenseFile = convertExcelFileToExpenseFile.execute(fileXLS)
        expenseFile.objectKey = objectKey
        saveExpenseFile.execute(expenseFile)
    }
}