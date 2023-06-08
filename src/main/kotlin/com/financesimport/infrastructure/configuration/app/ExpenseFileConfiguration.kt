package com.financesimport.infrastructure.configuration.app

import com.financesimport.core.gateway.ConvertExcelFileToExpenseFile
import com.financesimport.core.gateway.GetObjectStorage
import com.financesimport.core.gateway.SaveExpenseFile
import com.financesimport.core.usecase.ImportExpenseFileUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExpenseFileConfiguration {

    @Bean
    fun importExpenseFileUseCase(saveExpenseFile: SaveExpenseFile,
                                 convertExcelFileToExpenseFile: ConvertExcelFileToExpenseFile,
                                 getObjectStorage : GetObjectStorage) =
        ImportExpenseFileUseCase(saveExpenseFile, convertExcelFileToExpenseFile, getObjectStorage)
}