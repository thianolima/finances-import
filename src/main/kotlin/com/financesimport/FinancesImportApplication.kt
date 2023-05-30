package com.financesimport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class FinancesImportApplication

fun main(args: Array<String>) {
	runApplication<FinancesImportApplication>(*args)
}
