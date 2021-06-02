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
- `viewModel { }` : viewModel의 경우 
- `single { }` : 해당 객체를 싱글톤으로 지정 합니다.
- `factory { }` : 주입 시점마다 새로운 객체를 매번 생성 합니다.
- `get()` : 컴포넌트 내에서 알맞은 타입의 객체(의존성)를 주입 합니다.
- `inject()` : `get()`과 같이 알맞은 타입의 객체(의존성)를 주입 합니다.

## 동작 방식 

1. Module 선언(생성)
2. Application class 에서 startKoin { }
3. 의존성 주입

## Module 선언하기

`module` 에는 제공(주입)하기 위한 객체들을 선언할 수 있습니다.

해당 예제는 `SampleLocalDataSourceImpl` 클래스와 이 클래스를 생성자로 받는 `SampleRepositoryImpl`과 viewModel 클래스인 `MainViewModel`을 하나 만들어 모듈로 등록후 사용해 보겠습니다.

```kotlin
interface SampleLocalDataSource {
    fun getPerson(name: String, age: Int): Person
}

class SampleLocalDataSourceImpl : SampleLocalDataSource {
    override fun getPerson(name: String, age: Int): Person {
        return Person(name, age)
    }
}

interface SampleRepository {
    fun getPerson(name: String, age: Int) : Person
}

class SampleRepositoryImpl(private val dataSource: SampleLocalDataSource) : SampleRepository {
    override fun getPerson(name: String, age: Int): Person {
        return dataSource.getPerson(name, age)
    }
}
```

```kotlin
class MainViewModel(private val repo: SampleRepository) : ViewModel() {
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
        _text.value = repo.getPerson("이씨", 17).toString()
    }
}
```

위와같이 주입하고자 샘플 클래스를 만들고 `module` 키워드를 통해 모듈로 선언하고 변수에 저장합니다.

```kotlin
val appModule = module {
    // 싱글톤으로 SampleLocalDataSource 타입의 SampleLocalDataSourceImpl() 객체 사용
    single<SampleLocalDataSource> { SampleLocalDataSourceImpl() }
    // 싱글톤으로 SampleRepository 타입의 SampleRepositoryImpl() 객체 사용
    // 파라미터에 필요한 객체를 get()으로 알맞은 타입을 가져온다.(위와 같이 선언 된 경우)
    single<SampleRepository> { SampleRepositoryImpl(get()) } 
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
```

## 모듈 등록

위에서 선언한 모듈들을 koin에 등록해 줘야 합니다.

`Application class의 onCreate()에서` startKoin 호출 하고 위에서 선언한 모듈 변수를 넘겨 줍니다.

```kotlin
class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                printLogger(Level.DEBUG) // 코인이 로그를 남기는 레벨을 지정합니다.
            }

            //사용할 context 등록
            androidContext(this@KoinApplication)

            // 사용할 모듈 등록
            // modules(appModule, viewModelModule) 처럼 한번에도 가능
            modules(appModule)
            modules(viewModelModule)
        }
    }
}
```

## 의존성 주입 받기

single, factory 로 선언한 경우 `by inject` 또는 `get()` 을 사용 합니다

viewModel은 by viewModel() 을 사용합니다.

```kotlin
class MainActivity : AppCompatActivity() {

    // viewModel 주입
    private val viewModel: MainViewModel by viewModel()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // SampleRepository 주입
    private val repo: SampleRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        observe()
    }

    private fun showToast() {
        repo.getPerson("a", 10).let {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showToast2() {
        // SampleRepository 가져오기
        val repo2: SampleRepository = get()
    }

    private fun initViews() = with(binding) {
        btnShowLoading.setOnClickListener { viewModel.startLoading() }
        btnDismissLoading.setOnClickListener { viewModel.stopLoading() }
        btnPerson.setOnClickListener { viewModel.getPerson() }
    }

    private fun observe() = with(viewModel) {
        isLoading.observe(this@MainActivity) {
            binding.pbLoading.isVisible = it
        }

        text.observe(this@MainActivity) {
            binding.tvPerson.text = it
        }
    }
}
```




---
참고

[홍범님 미디엄](https://medium.com/hongbeomi-dev/koin-%EC%9E%98-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-5a96a8bb94d3)

[spoqa 기술 블로그](https://spoqa.github.io/2020/11/02/android-dependency-injection-with-koin.html)

[날고싶은 개발자님 블로그](https://jaejong.tistory.com/153)

[koin공홈]:https://insert-koin.io/docs/setup/v3
[DSL]:https://www.jetbrains.com/ko-kr/mps/concepts/domain-specific-languages/
