package com.financesimport.infrastructure.entrypoint.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class EventCreateS3Dto (
    @field:JsonProperty("Records") val records : ArrayList<EventDto>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventDto(
    @field:JsonProperty("eventTime") val eventTime : String,
    @field:JsonProperty("s3") val s3 : S3Dto,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class S3Dto(
    @field:JsonProperty("bucket") val bucketS3 : BucketS3Dto,
    @field:JsonProperty("object") val objectS3 : ObjectS3Dto
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class BucketS3Dto(
    @field:JsonProperty("name") val name : String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ObjectS3Dto(
    @field:JsonProperty("key") val key : String
)
