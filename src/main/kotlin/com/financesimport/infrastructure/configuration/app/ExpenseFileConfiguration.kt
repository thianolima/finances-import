package com.financesimport.infrastructure.configuration.app

import com.financesimport.core.gateway.SaveExpenseFile
import com.financesimport.core.usecase.InsertExpenseFileUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExpenseFileConfiguration {

    @Bean
    fun insertExpenseFileUseCase(saveExpenseFile: SaveExpenseFile) =
        InsertExpenseFileUseCase(saveExpenseFile)
}