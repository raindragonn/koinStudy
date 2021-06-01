package com.raindragonn.koinstudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raindragonn.koinstudy.data.RepositoryImpl

// Created by raindragonn on 2021/06/01.
class MainViewModel(private val repo: RepositoryImpl) : ViewModel() {
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _text: MutableLiveData<String> = MutableLiveData("모름")
    val text: LiveData<String>
        get() = _text

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    fun getPerson() {
        _text.value = repo.getPerson().toString()
    }

}

