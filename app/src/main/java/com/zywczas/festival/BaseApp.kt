package com.zywczas.festival

import android.app.Application
import com.zywczas.festival.di.appKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(appKoinModules)
        }
    }
}
