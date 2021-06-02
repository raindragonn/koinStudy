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
            if (BuildConfig.DEBUG) {
                printLogger(Level.DEBUG) // 코인이 로그를 남기는 레벨을 지정합니다.
            }

            //사용할 context 등록
            androidContext(this@KoinApplication)

            // 사용할 모듈 등록
            // modules(appModule, viewModelModule) 처럼 한번에도 가능
            modules(appModule)
            modules(viewModelModule)
        }
    }
}