package com.tolentinoluisi.concurrenttranslator.framework.service

import com.tolentinoluisi.concurrenttranslator.data.response.Languages
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_GET_LANGUAGES
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_HEADER_HOST
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_HEADER_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface TranslatorService {

    @Headers(
        SERVICE_HEADER_HOST, SERVICE_HEADER_KEY
    )
    @GET(SERVICE_GET_LANGUAGES)
    suspend fun getLanguages(): Response<Languages>
}