package com.financesimport.infrastructure.entrypoint.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class EventCreateS3Dto (
    @field:JsonProperty("Records") val records : ArrayList<EventDto>
)

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(value = ["eventVersion","eventSource", "awsRegion", "eventName", "userIdentity", "requestParameters", "responseElements"])
data class EventDto(
    @field:JsonProperty("eventTime") val eventTime : String,
    @field:JsonProperty("s3") val s3 : S3Dto,
)

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(value = ["s3SchemaVersion", "configurationId"])
data class S3Dto(
    @field:JsonProperty("bucket") val bucketS3 : BucketS3Dto,
    @field:JsonProperty("object") val objectS3 : ObjectS3Dto
)

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(value = ["ownerIdentity", "arn"])
data class BucketS3Dto(
    @field:JsonProperty("name") val name : String
)

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(value = ["size", "eTag", "sequencer", "versionId"])
data class ObjectS3Dto(
    @field:JsonProperty("key") val key : String
)
