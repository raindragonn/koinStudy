package com.raindragonn.koinstudy.data

import com.raindragonn.koinstudy.data.model.Person

// Created by raindragonn on 2021/06/01.

interface SampleRepository {
    fun getPerson(name: String, age: Int) : Person
}