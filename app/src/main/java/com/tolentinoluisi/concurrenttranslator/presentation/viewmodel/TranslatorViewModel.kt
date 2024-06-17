package com.tolentinoluisi.concurrenttranslator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolentinoluisi.concurrenttranslator.domain.entity.Language
import com.tolentinoluisi.concurrenttranslator.domain.usecase.TranslatorUseCase
import com.tolentinoluisi.concurrenttranslator.domain.utils.flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TranslatorViewModel(private val useCase: TranslatorUseCase) : ViewModel() {

    private val _state = MutableStateFlow<TranslatorState>(TranslatorState.HideLoading)
    val state = _state.asStateFlow()

    fun getLanguages() = viewModelScope.launch {
        _state.value = TranslatorState.ShowLoading
        val result = useCase.getLanguages()
        result.flow(
            { languages ->
                _state.value = TranslatorState.HideLoading
                _state.value = TranslatorState.LanguagesSuccess(languages)
            },
            {
                _state.value = TranslatorState.HideLoading
                _state.value = TranslatorState.LanguagesError
            }
        )
    }

    fun translate(text: String, from: Language, to: Language) = viewModelScope.launch {
        _state.value = TranslatorState.ShowLoading
        val result = useCase.translate(text, from, to)
        result.flow(
            { outputText ->
                _state.value = TranslatorState.HideLoading
                _state.value = TranslatorState.TranslateSuccess(outputText)
            }, {
                _state.value = TranslatorState.HideLoading
                _state.value = TranslatorState.TranslateError
            }
        )
    }
}