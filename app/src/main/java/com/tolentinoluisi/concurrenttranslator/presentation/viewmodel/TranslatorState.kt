package com.tolentinoluisi.concurrenttranslator.presentation.viewmodel

import com.tolentinoluisi.concurrenttranslator.domain.entity.Language

sealed class TranslatorState {

    data class TranslateSuccess(val outputText: String) : TranslatorState()
    data class LanguagesSuccess(val languages: List<Language>) : TranslatorState()
    data object TranslateError : TranslatorState()
    data object LanguagesError : TranslatorState()
    data object ShowLoading : TranslatorState()
    data object HideLoading : TranslatorState()
}
