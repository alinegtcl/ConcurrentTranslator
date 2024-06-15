package com.tolentinoluisi.concurrenttranslator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TranslatorViewModel : ViewModel() {

    private val _state = MutableStateFlow<TranslatorState>(TranslatorState.HideLoading)
    val state = _state.asStateFlow()

    fun getLanguages() = viewModelScope.launch {
        _state.value = TranslatorState.ShowLoading
        val result = listOf("English", "Spanish", "Portuguese")
        _state.value = TranslatorState.HideLoading
        _state.value = TranslatorState.LanguagesSuccess(result)
    }

    fun translate(text: String, from: String, to: String) = viewModelScope.launch {
        _state.value = TranslatorState.ShowLoading
        val result = "Texto traduzido"
        _state.value = TranslatorState.HideLoading
        _state.value = TranslatorState.TranslateSuccess(result)
    }
}