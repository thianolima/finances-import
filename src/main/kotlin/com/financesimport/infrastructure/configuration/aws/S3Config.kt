package com.financesimport.infrastructure.configuration.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import java.time.Duration

@Configuration
class S3Config(
    val awsCredentials : AwsCredentialsProvider
) {

    @Bean
    fun s3Client() : S3Client{
        return S3Client
            .builder()
            .overrideConfiguration(ClientOverrideConfiguration.builder().apiCallTimeout(Duration.ofSeconds(30)).build())
            .credentialsProvider(awsCredentials)
            .build()
    }

    @Bean
    fun s3Presigner() : S3Presigner{
        return S3Presigner
            .builder()
            .credentialsProvider(awsCredentials)
            .build()
    }

    @Bean
    fun amazonS3(): AmazonS3 {
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("fake-access", "fake-secret")))
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566","us-east-1"))
            .withPathStyleAccessEnabled(true)
            .build();
    }
}