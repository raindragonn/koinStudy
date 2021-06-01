package com.raindragonn.koinstudy.module

import com.raindragonn.koinstudy.data.RepositoryImpl
import com.raindragonn.koinstudy.data.SampleDataSource
import org.koin.dsl.module

// Created by raindragonn on 2021/06/01.


val appModule = module {
    single { SampleDataSource() }
    single { RepositoryImpl(get()) }
}