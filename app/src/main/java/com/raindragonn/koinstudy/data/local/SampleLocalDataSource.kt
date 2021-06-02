package com.raindragonn.koinstudy.data.local

import com.raindragonn.koinstudy.data.model.Person

// Created by raindragonn on 2021/06/02.

interface SampleLocalDataSource {
    fun getPerson(name: String, age: Int): Person
}