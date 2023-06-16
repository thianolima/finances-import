package com.financesimport.infrastructure.entrypoint.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class EventCreateS3Message (
    @field:JsonProperty("Records") val records : ArrayList<EventMessage>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventMessage(
    @field:JsonProperty("eventTime") val eventTime : String,
    @field:JsonProperty("s3") val s3 : S3Message,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class S3Message(
    @field:JsonProperty("bucket") val bucketS3 : BucketS3Message,
    @field:JsonProperty("object") val objectS3 : ObjectS3Message
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class BucketS3Message(
    @field:JsonProperty("name") val name : String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ObjectS3Message(
    @field:JsonProperty("key") val key : String
)
