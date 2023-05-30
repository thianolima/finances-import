package com.financesimport.core.gateway

import java.net.URL

interface GeneratePresignedUploadUrl {
    fun execute() : URL
}