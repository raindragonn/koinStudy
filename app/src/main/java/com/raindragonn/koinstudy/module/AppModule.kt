package com.raindragonn.koinstudy.module

import com.raindragonn.koinstudy.data.SampleRepository
import com.raindragonn.koinstudy.data.SampleRepositoryImpl
import com.raindragonn.koinstudy.data.local.SampleLocalDataSource
import com.raindragonn.koinstudy.data.local.SampleLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Created by raindragonn on 2021/06/01.


val appModule = module {
    // 싱글톤으로 SampleLocalDataSource 타입의 SampleLocalDataSourceImpl 객체 사용
    // named()를 이용해 qualifier(한정자) 지정 가능
    single<SampleLocalDataSource>(qualifier = named("dataSource")) { SampleLocalDataSourceImpl() }

    // 싱글톤으로 SampleRepository 타입의 SampleRepositoryImpl 객체 사용
    // SampleRepositoryImpl의 생성자로 필요한 SampleLocalDataSource 객체를
    // 위 에서 선언한 한정자를 토대로 가져옴
    single<SampleRepository> { SampleRepositoryImpl(get(qualifier = named("dataSource"))) }


//    // 싱글톤으로 SampleLocalDataSource 타입의 SampleLocalDataSourceImpl 객체를 사용(주입) 하겠다.
//    single<SampleLocalDataSource> { SampleLocalDataSourceImpl() }
//
//    // 싱글톤으로 SampleRepository 타입의 SampleRepositoryImpl 객체를 사용(주입) 하겠다.
//    // get()을 통해 필요한 파라미터의 타입에 알맞은 객체를 주입해줍니다.(해당 타입으로 선언된 객체가 필요)
//    // 위에서 선언한 SampleLocalDataSourceImpl 이 들어감
//    single<SampleRepository> { SampleRepositoryImpl(get()) }

}