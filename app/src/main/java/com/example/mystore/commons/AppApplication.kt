package com.example.mystore.commons

import android.app.Application
import com.example.mystore.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    companion object {
        lateinit var instance: AppApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}
