package com.financesimport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableFeignClients
class FinancesImportApplication

fun main(args: Array<String>) {
	runApplication<FinancesImportApplication>(*args)
}
