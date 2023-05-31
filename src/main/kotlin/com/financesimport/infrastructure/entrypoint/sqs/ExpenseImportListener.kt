package com.financesimport.infrastructure.entrypoint.sqs

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.financesimport.core.usecase.ImportExpenseFileUseCase
import com.financesimport.infrastructure.entrypoint.dto.EventCreateS3Dto
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ExpenseImportListener(
    val importExpenseFileUseCase : ImportExpenseFileUseCase,
) {
    @SqsListener(value = ["upload-file-event-sqs"])
    fun listener(@Payload message : String, @Headers headers: Map<String, String>){
        val record = jacksonObjectMapper().readValue<EventCreateS3Dto>(message).records.first()
        importExpenseFileUseCase.execute(record.s3.bucketS3.name, record.s3.objectS3.key)
    }
}