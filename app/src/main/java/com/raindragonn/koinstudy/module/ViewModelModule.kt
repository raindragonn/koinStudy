package com.raindragonn.koinstudy.module

import com.raindragonn.koinstudy.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Created by raindragonn on 2021/06/01.


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}