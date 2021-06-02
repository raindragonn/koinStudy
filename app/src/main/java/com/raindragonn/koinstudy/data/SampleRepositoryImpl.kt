package com.raindragonn.koinstudy.data

import com.raindragonn.koinstudy.data.local.SampleLocalDataSource
import com.raindragonn.koinstudy.data.model.Person

// Created by raindragonn on 2021/06/01.

class SampleRepositoryImpl(private val dataSource: SampleLocalDataSource) : SampleRepository {
    override fun getPerson(name: String, age: Int): Person {
        return dataSource.getPerson(name, age)
    }
}
