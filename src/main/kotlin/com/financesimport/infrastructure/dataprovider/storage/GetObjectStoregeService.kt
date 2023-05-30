package com.financesimport.infrastructure.dataprovider.storage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.financesimport.core.exception.DomainException
import com.financesimport.core.gateway.GetObjectStorage
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class GetObjectStoregeService(
    private val amazonS3 : AmazonS3
) : GetObjectStorage {
    override fun execute(bucketName: String, objectKey: String): InputStream {
        try {
            return amazonS3.getObject(bucketName, objectKey).objectContent
        } catch (ex : AmazonS3Exception) {
            throw DomainException("Arquivo nao encontrado no s3")
        }
    }
}