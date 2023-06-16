package com.financesimport.core.usecase

import com.financesimport.core.gateway.GeneratePresignedUploadUrl

class UploadExpenseFileUseCase(
    val generatePresignedUploadUrl: GeneratePresignedUploadUrl
) {

    fun execute() =
        generatePresignedUploadUrl.execute()
}
