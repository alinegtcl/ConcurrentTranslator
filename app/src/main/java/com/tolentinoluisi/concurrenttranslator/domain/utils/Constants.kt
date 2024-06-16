package com.tolentinoluisi.concurrenttranslator.domain.utils

object Constants {
    const val EMPTY_STRING = ""
    const val MEDIATYPE_MULTIPART_FORM_DATA = "multipart/form-data"
    const val SOURCE_LANGUAGE = "source_language"
    const val TARGET_LANGUAGE = "target_language"
    const val TEXT = "text"
    const val BASE_URL_SERVICE = "https://text-translator2.p.rapidapi.com"
    const val SERVICE_GET_LANGUAGES = "/getLanguages"
    const val SERVICE_TRANSLATE = "/translate"
    const val SERVICE_HEADER_HOST = "x-rapidapi-host: text-translator2.p.rapidapi.com"
    const val SERVICE_HEADER_KEY =
        "x-rapidapi-key: 8e09ec6314msh076d605022ca7b1p174728jsn082b6a5a38d8"
}