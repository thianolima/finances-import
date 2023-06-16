package com.financesimport.core.usecase

import com.financesimport.core.gateway.ConvertExcelFileToExpenseFile
import com.financesimport.core.gateway.GetObjectStorage
import com.financesimport.core.gateway.SaveExpenseFile
import com.financesimport.core.gateway.SynchronizeExpenseFile
import org.slf4j.LoggerFactory

class ImportExpenseFileUseCase(
    val saveExpenseFile : SaveExpenseFile,
    val convertExcelFileToExpenseFile: ConvertExcelFileToExpenseFile,
    val getObjectStorage : GetObjectStorage,
    val synchronize : SynchronizeExpenseFile
) {
    fun execute(bucketName : String, objectKey : String){
        log.info("downloading excel file from storage")
        val fileXLS = getObjectStorage.execute(bucketName, objectKey)
        log.info("converting excel file to expense file ")
        val expenseFile = convertExcelFileToExpenseFile.execute(fileXLS, objectKey)
        log.info("synchronizing expense file items")
        val expenseFileSync = synchronize.execute(expenseFile)
        log.info("saving expense file")
        saveExpenseFile.execute(expenseFileSync)
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}