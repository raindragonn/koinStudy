package com.raindragonn.koinstudy

import android.app.Application
import com.raindragonn.koinstudy.module.appModule
import com.raindragonn.koinstudy.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

// Created by raindragonn on 2021/06/01.

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@KoinApplication)
            modules(appModule)
            modules(viewModelModule)
        }
    }
}