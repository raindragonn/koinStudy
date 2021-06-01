## koin 이란?

Android 에서 주로 사용되는 의존선 주입 라이브러리 중 하나입니다.

버전 확인 및 도큐먼트 확인은 [공식 페이지][koin공홈] 에서 확인할 수 있습니다.

## SetUp

build.gradle(project)
```gradle
buildscript {
    ext.kotlin_version = "1.4.32"
    ext.koin_version = "3.0.2" // 사용하는 코인의 버전

    repositories {
        mavenCentral()  //
    }
}
```

```gradle
dependencies {
    //...
    // core
    implementation "io.insert-koin:koin-core:$koin_version"

    // Scope,ViewModel
    implementation "io.insert-koin:koin-android:$koin_version"

    // ext
    implementation "io.insert-koin:koin-android-ext:$koin_version"

    // Androidx Workmanager
    implementation "io.insert-koin:koin-androidx-workmanager:$koin_version"

    // Test
    testImplementation "io.insert-koin:koin-test:$koin_version"
}
```

## DSL 키워드

**koin은 [DSL][DSL]을 사용하여 의존성을 관리합니다.**

- `module { }` : koin 모듈, 주입할 객체를 의미
- `viewModel { }` : Activity나 Fragment에 ViewModel 주입
- `single { }` : 해당 객체를 싱글톤으로 지정 합니다.
- `factory { }` : 주입 시점마다 새로운 객체를 매번 생성 합니다.
- `get()` : 컴포넌트 내에서 알맞은 타입의 객체(의존성)를 주입 합니다.
- `inject()` : `get()`과 같이 알맞은 타입의 객체(의존성)를 주입 합니다.





---
참고



[koin공홈]:https://insert-koin.io/docs/setup/v3
[DSL]:https://www.jetbrains.com/ko-kr/mps/concepts/domain-specific-languages/