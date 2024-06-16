package com.tolentinoluisi.concurrenttranslator.data.response

data class Languages(
    val data: Data
) {
    data class Data(
        val languages: List<LanguageResponse>
    ) {
        data class LanguageResponse(
            val code: String,
            val name: String
        )
    }
}