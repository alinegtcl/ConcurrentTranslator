package com.tolentinoluisi.concurrenttranslator.domain.usecase

import com.tolentinoluisi.concurrenttranslator.domain.entities.Language
import com.tolentinoluisi.concurrenttranslator.domain.utils.Result

interface TranslatorUseCase {
    suspend fun getLanguages(): Result<List<Language>, String>

    suspend fun translate(
        inputText: String,
        source: Language,
        target: Language
    ): Result<String, String>
}