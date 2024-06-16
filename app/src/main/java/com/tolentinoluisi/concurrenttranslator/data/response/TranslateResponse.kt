package com.tolentinoluisi.concurrenttranslator.data.response

data class TranslateResponse(
    val data: Data,
    val status: String
) {
    data class Data(
        val translatedText: String
    )
}