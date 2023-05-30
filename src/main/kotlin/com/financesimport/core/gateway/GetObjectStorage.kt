package com.financesimport.core.gateway

import io.awspring.cloud.s3.S3Exception
import java.io.InputStream
import kotlin.jvm.Throws

interface GetObjectStorage {
    @Throws(S3Exception::class)
    fun execute(bucketName: String, objectKey: String) : InputStream
}