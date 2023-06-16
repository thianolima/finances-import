package com.financesimport.infrastructure.dataprovider.storage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.financesimport.core.exception.DomainException
import com.financesimport.core.gateway.GetObjectStorage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class GetObjectStoregeService(
    private val amazonS3 : AmazonS3
) : GetObjectStorage {
    override fun execute(bucketName: String, objectKey: String): InputStream {
        try {
            log.info("get object from bucketname $bucketName and objectkey $objectKey")
            return amazonS3.getObject(bucketName, objectKey).objectContent
        } catch (ex : AmazonS3Exception) {
            log.error("error when get object from bucketname $bucketName and objectkey $objectKey error ${ex.message}")
            throw DomainException("Arquivo nao encontrado no s3")
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}