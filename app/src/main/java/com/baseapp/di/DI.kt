package com.baseapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object DI {
    fun initialize(application: Application) {
        startKoin {
            androidContext(application)
        }
    }
}