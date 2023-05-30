package com.financesimport.infrastructure.entrypoint.sqs

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.financesimport.core.gateway.ConvertExcelFileToExpenseFile
import com.financesimport.core.gateway.GetObjectStorage
import com.financesimport.core.usecase.InsertExpenseFileUseCase
import com.financesimport.infrastructure.entrypoint.dto.EventCreateS3Dto
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ExpenseImportListener(
    val insertExpenseFileUseCase : InsertExpenseFileUseCase,
    val getObjectStorage : GetObjectStorage,
    val convertExcelFileToExpenseFile: ConvertExcelFileToExpenseFile
) {
    @SqsListener(value = ["upload-file-event-sqs"])
    fun listener(@Payload message : String, @Headers headers: Map<String, String>){
        val record = jacksonObjectMapper().readValue<EventCreateS3Dto>(message).records.first()
        val fileXLS = getObjectStorage.execute(record.s3.bucketS3.name, record.s3.objectS3.key)
        val expenseFile = convertExcelFileToExpenseFile.execute(fileXLS)
        expenseFile.objectKey = record.s3.objectS3.key
        insertExpenseFileUseCase.execute(expenseFile)
    }
}