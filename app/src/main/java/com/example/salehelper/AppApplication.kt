package com.example.salehelper

import android.app.Application
import com.example.salehelper.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}
