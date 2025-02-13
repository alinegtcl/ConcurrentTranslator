package com.tolentinoluisi.concurrenttranslator.framework.service

import com.tolentinoluisi.concurrenttranslator.data.response.Languages
import com.tolentinoluisi.concurrenttranslator.data.response.TranslateResponse
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_GET_LANGUAGES
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_HEADER_HOST
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_HEADER_KEY
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SERVICE_TRANSLATE
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.SOURCE_LANGUAGE
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.TARGET_LANGUAGE
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.TEXT
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TranslatorService {

    @Headers(SERVICE_HEADER_HOST, SERVICE_HEADER_KEY)
    @GET(SERVICE_GET_LANGUAGES)
    suspend fun getLanguages(): Response<Languages>

    @Multipart
    @Headers(SERVICE_HEADER_HOST, SERVICE_HEADER_KEY)
    @POST(SERVICE_TRANSLATE)
    suspend fun translate(
        @Part(SOURCE_LANGUAGE) sourceLanguage: RequestBody,
        @Part(TARGET_LANGUAGE) targetLanguage: RequestBody,
        @Part(TEXT) text: RequestBody
    ): Response<TranslateResponse>
}