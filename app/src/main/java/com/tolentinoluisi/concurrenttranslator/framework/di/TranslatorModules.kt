package com.tolentinoluisi.concurrenttranslator.framework.di

import com.tolentinoluisi.concurrenttranslator.presentation.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { TranslatorViewModel() }
}