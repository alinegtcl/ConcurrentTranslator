package com.tolentinoluisi.concurrenttranslator.data.repository

import com.tolentinoluisi.concurrenttranslator.data.response.Languages
import com.tolentinoluisi.concurrenttranslator.domain.entity.Language
import com.tolentinoluisi.concurrenttranslator.domain.usecase.TranslatorUseCase
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.EMPTY_STRING
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.MEDIATYPE_MULTIPART_FORM_DATA
import com.tolentinoluisi.concurrenttranslator.domain.utils.Result
import com.tolentinoluisi.concurrenttranslator.framework.service.TranslatorService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class TranslatorRepositoryImpl(private val service: TranslatorService) : TranslatorUseCase {
    override suspend fun getLanguages(): Result<List<Language>, String> {
        return try {
            val result = service.getLanguages()
            if (result.isSuccessful) {
                Result.Success(result.body()?.toEntity() ?: emptyList())
            } else {
                Result.Error(result.errorBody()?.toString() ?: EMPTY_STRING)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: EMPTY_STRING)
        }

    }

    override suspend fun translate(
        inputText: String,
        source: Language,
        target: Language
    ): Result<String, String> {
        val mediaType = MEDIATYPE_MULTIPART_FORM_DATA.toMediaTypeOrNull()

        val sourceLanguage = source.code.toRequestBody(mediaType)
        val targetLanguage = target.code.toRequestBody(mediaType)
        val text = inputText.toRequestBody(mediaType)

        return try {
            val result = service.translate(sourceLanguage, targetLanguage, text)
            return if (result.isSuccessful) {
                Result.Success(result.body()?.data?.translatedText ?: EMPTY_STRING)
            } else {
                Result.Error(result.errorBody()?.toString() ?: EMPTY_STRING)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: EMPTY_STRING)
        }
    }
}

private fun Languages?.toEntity(): List<Language> {
    return this?.data?.languages?.map { it.toLanguage() } ?: emptyList()
}

private fun Languages.Data.LanguageResponse.toLanguage(): Language {
    return Language(name = this.name, code = this.code)
}




