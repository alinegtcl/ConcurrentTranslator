package com.tolentinoluisi.concurrenttranslator.presentation.viewmodel

sealed class TranslatorState {

    data class TranslateSuccess(val outputText: String) : TranslatorState()
    data class LanguagesSuccess(val languages: List<String>) : TranslatorState()
    data object TranslatorError : TranslatorState()
    data object LanguagesError : TranslatorState()
    data object ShowLoading : TranslatorState()
    data object HideLoading : TranslatorState()
}
