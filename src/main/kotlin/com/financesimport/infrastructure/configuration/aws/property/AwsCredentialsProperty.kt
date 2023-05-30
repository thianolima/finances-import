package com.financesimport.infrastructure.configuration.aws.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.cloud.aws.credentials")
data class AwsCredentialsProperty (
    val accessKey: String,
    val secretKey: String
)