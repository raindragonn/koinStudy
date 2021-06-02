package com.raindragonn.koinstudy.data.local

import com.raindragonn.koinstudy.data.model.Person

// Created by raindragonn on 2021/06/01.

class SampleLocalDataSourceImpl : SampleLocalDataSource {
    override fun getPerson(name: String, age: Int): Person {
        return Person(name, age)
    }
}