package com.financesimport.infrastructure.configuration.aws.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.cloud.aws.s3")
class S3Property (
    val region: String,
    val endpoint: String
){

}