package com.financesimport.infrastructure.configuration.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.financesimport.infrastructure.configuration.aws.property.AwsCredentialsProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AwsConfig (
    val awsCredentialsProperty: AwsCredentialsProperty
){
    @Bean
    fun awsCredentials(): AWSStaticCredentialsProvider {
        return AWSStaticCredentialsProvider(BasicAWSCredentials(
            awsCredentialsProperty.accessKey,
            awsCredentialsProperty.secretKey)
        )
    }
}