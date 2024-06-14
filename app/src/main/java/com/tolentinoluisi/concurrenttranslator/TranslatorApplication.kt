package com.tolentinoluisi.concurrenttranslator

import android.app.Application
import com.tolentinoluisi.concurrenttranslator.framework.di.appModules
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TranslatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TranslatorApplication)
            modules(appModules)
        }
    }
}