package com.raindragonn.koinstudy.data

// Created by raindragonn on 2021/06/01.

class RepositoryImpl(private val dataSource: SampleDataSource) : Repository {
    override fun getPerson(): Person {
        return dataSource.createPerson()
    }
}
