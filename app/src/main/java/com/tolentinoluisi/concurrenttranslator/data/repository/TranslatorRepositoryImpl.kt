package com.tolentinoluisi.concurrenttranslator.data.repository

import com.tolentinoluisi.concurrenttranslator.data.response.Languages
import com.tolentinoluisi.concurrenttranslator.domain.entities.Language
import com.tolentinoluisi.concurrenttranslator.domain.usecase.TranslatorUseCase
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.EMPTY_STRING
import com.tolentinoluisi.concurrenttranslator.domain.utils.Result
import com.tolentinoluisi.concurrenttranslator.framework.service.TranslatorService

class TranslatorRepositoryImpl(private val service: TranslatorService) : TranslatorUseCase {
    override suspend fun getLanguages(): Result<List<Language>, String> {
        val result = service.getLanguages()
        return if (result.isSuccessful) {
            Result.Success(result.body()?.toEntity() ?: emptyList())
        } else {
            Result.Error(result.errorBody()?.toString() ?: EMPTY_STRING)
        }
    }
}

private fun Languages?.toEntity(): List<Language> {
    return this?.data?.languages?.map { it.toLanguage() } ?: emptyList()
}

private fun Languages.Data.LanguageResponse.toLanguage(): Language {
    return Language(name = this.name, code = this.code)
}




