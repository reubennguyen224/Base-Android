package com.baseapp

import android.app.Application
import com.baseapp.di.DI
import timber.log.Timber

class BaseApp : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        DI.initialize(this)
    }
}