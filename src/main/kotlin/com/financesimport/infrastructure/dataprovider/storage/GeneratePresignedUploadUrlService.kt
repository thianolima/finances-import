package com.financesimport.infrastructure.dataprovider.storage

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.financesimport.core.gateway.GeneratePresignedUploadUrl
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.s3.S3Client
import java.net.URL
import java.time.Instant
import java.util.*

@Service
class GeneratePresignedUploadUrlService (
    val amazonS3: AmazonS3
) : GeneratePresignedUploadUrl {

    override fun execute(): URL {
        val bucketName = "invoices"
        val fileName = UUID.randomUUID().toString()
        val expiration = Date.from(Instant.now().plusSeconds(120))

        if (!amazonS3.doesBucketExistV2(bucketName)){
            amazonS3.createBucket(bucketName)
        }

        return URL(amazonS3.generatePresignedUrl(bucketName, fileName, expiration, HttpMethod.PUT).toString())
    }
}