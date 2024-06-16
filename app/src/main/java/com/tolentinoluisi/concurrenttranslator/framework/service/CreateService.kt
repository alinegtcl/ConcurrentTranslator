package com.tolentinoluisi.concurrenttranslator.framework.service

import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.BASE_URL_SERVICE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateService {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun buildService(): TranslatorService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_SERVICE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(TranslatorService::class.java)
    }
}