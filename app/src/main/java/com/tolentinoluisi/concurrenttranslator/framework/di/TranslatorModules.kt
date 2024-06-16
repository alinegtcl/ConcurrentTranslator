package com.tolentinoluisi.concurrenttranslator.framework.di

import com.tolentinoluisi.concurrenttranslator.data.repository.TranslatorRepositoryImpl
import com.tolentinoluisi.concurrenttranslator.domain.usecase.TranslatorUseCase
import com.tolentinoluisi.concurrenttranslator.framework.service.CreateService
import com.tolentinoluisi.concurrenttranslator.presentation.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { CreateService().buildService() }
    factory<TranslatorUseCase> { TranslatorRepositoryImpl(get()) }
    viewModel { TranslatorViewModel(get()) }
}