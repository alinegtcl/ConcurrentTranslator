package com.tolentinoluisi.concurrenttranslator.framework.service

import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.BASE_URL_SERVICE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateService {

    fun buildService(): TranslatorService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(TranslatorService::class.java)
    }
}