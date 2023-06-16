package com.financesimport.infrastructure.entrypoint.controller

import com.financesimport.core.gateway.GeneratePresignedUploadUrl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URL

@RestController
@RequestMapping("/expenses/import")
class ExpeseFileController(
    val generatePresignedUploadUrl: GeneratePresignedUploadUrl
) {

    @GetMapping
    fun generateUrl(): URL {
        return generatePresignedUploadUrl.execute()
    }
}