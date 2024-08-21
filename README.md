# Полезные ссылки

* [dagger hilt](https://dagger.dev/hilt/)
* [detekt](https://plugins.jetbrains.com/plugin/10761-detekt)
* [Безопасная маршрутизация](https://developer.android.com/guide/navigation/design/type-safety)
* [Локальное хранилише](https://developer.android.com/topic/libraries/architecture/datastore)
* [SvgToCompose](https://www.composables.com/svgtocompose)

# Lint

* В проекте установлен detekt (app/config/detekt/detekt.yml)
* Для его инициализации в android studio нужно скачать плагин [detekt](https://plugins.jetbrains.com/plugin/10761-detekt)
* Его активация описана в описании плагина

# Логирование

* В release сборке логирования - нет.
* Log.{} - убирается в конфиге proguard-rules.pro
* Логирование сетевых запросов убирается проверкой BuildConfig.DEBUG в файле api/network/HttpClient

# Тема, цвета, значения, иконки к темам, типография

* Все файлы находятся в папке providers/theme/Theme
* По умолчанию стоит темная тема
* Использование цветовой схемы:
  * Text(color = Theme.colors.primary)

# Routing

* Используется типобезопасные маршруты, смотри официальную [документацию](https://developer.android.com/guide/navigation/design/type-safety)
* Основной и единственный файл роутинга - navigation/Navigation
* Для контроля роутинга необходимо пользоваться методом LocalNavController.current из
  providers/LocalNavController
* Для перехода по страницам нужно использовать не строки, а импортировать обьект Routes из
  navigation/Routes

# TopBar || BottomBar

* Для каждого маршрута указан свой Top(Bottom)Bar в файле ui/common/top(bottom)Bar/Top(Botton)Bar.kt
* Для маршрутов стоит по умолчанию компонент

```kotlin
@Composable
fun TopBar() {
    val currentRoute = getCurrentRoute()
  
    when (currentRoute) {
        Routes.Auth.Profile::class.qualifiedName -> ProfileTopBar()
  
        else -> MainTopBar()
    }
}
```

# LocalStorage

## Для локального хранения используются две [библиотеки](https://developer.android.com/topic/libraries/architecture/datastore) - DataStore (ключ: значение) и ProtoDataStore (data class, enum class)

* Эти библиотеки интегрированы в проект с помощью hilt >> module/(Proto)Store.kt
* Они работают в IO потоке

## Как они работают в целом

* В проекте есть конструторы доступа к хранилищю (api/store/*.kt) - их трогать никогда не надо
* На вход они ожидают контекст и ключ доступа (обычный дата класс с заранее определенными параметрами)
* Смотря на этот ключ код будет определять куда смотреть и что отдавать

## Что такое ключ

* Ключ - представление в виде ключей, используемых для хранения и извлечения данных в хранилище.
* Каждый ключ связан с определенным типом данных, значением по умолчанию и сериализатором (Сериализатор только для ProtoStore).

* Ключи находятся в repository/store/(Proto)Keys.kt
* В этой папке есть файл GenericSerializer - нужен только для сериализации Proto данных (data class, enum class)
* Также есть папка data - интерфейсы хранимых данных в ProtoStore
* Ключ в случае обычного Store(ключ: значение) - дополнительная документация о нем описана в JavaDoc в store/Keys.kt
*
```kotlin
sealed class Keys<T>( // Коллекция обычных ключей
    val key: Preferences.Key<T>,
    val defaultValue: T,
) {
    data object TOKEN : Keys<String>(
        key = stringPreferencesKey("TOKEN"), // Имя ключа хранимого в файле
        defaultValue = "", // Значение по умолчанию
    ) 
}
```

* Ключ в случае ProtoStore(data class, enum class) - дополнительная документация о нем описана в JavaDoc в store/ProtoKeys.kt

```kotlin
sealed class ProtoKeys<T>(
  val key: Preferences.Key<String>,
  val defaultValue: T,
  val serializer: Serializer<T>,
) {
  data object USER : ProtoKeys<User>(
    key = stringPreferencesKey("user"),
    defaultValue = User(),
    serializer = GenericSerializer(serializer<User>(), User()), // Сериализатор прописывается один раз
  )

  data object THEME : ProtoKeys<CurrentTheme>(
    key = stringPreferencesKey("theme"),
    defaultValue = CurrentTheme.NULL,
    serializer = GenericSerializer(serializer<CurrentTheme>(), CurrentTheme.NULL),
  )
}

@kotlinx.serialization.Serializable
enum class CurrentTheme {
  DARK,
  LIGHT,
  NULL,
}

@kotlinx.serialization.Serializable
data class User(
  val id: String = "",
  val username: String = "",
  val surname: String = "",
  val email: String = "",
)
```

## Использование

1. Во ViewModel получить нужный конструктор (Store, ProtoStore)
2. Использовать методы хранилища
```kotlin
@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeProtoStore: ProtoStore<CurrentTheme>, // 1 Получил экземляр хранилища, context и ключ ему передал hilt, о работе hilt смотри оф. доку
    @TokenStore private val tokenStore: Store<String>, // 1 Назначение аннотации описано в оф. документации hilt
) : ViewModel() {
    
    fun fetchTheme() {
        viewModelScope.launch {
            val currentTheme: CurrentTheme = themeProtoStore.get() // 2 Использование встроенных методов 
        }
    }
}
```

## Создание

* Для создания новых данных хранилища нужно сделать следующее
1. Создать ключ в (Proto)Keys.kt
2. Добавить новый ключ в module/(Proto)StoreModule.kt, согласно документации hilt
3. Все

# Бд

* В качестве базы данных предустановлен Room интегрированный в Hilt

# Network request

* Основной конструктор запросов: api/network/SafeRequest.kt - его трогать нельзя. От него следует
  создать другие запросы
* Интерфейс полей ответа находится в api/network/ApiResponse
* Создание запроса:
  * В папке repository/requests создать файл с функцией такого типа:

```kotlin
@kotlinx.serialization.Serializable
data class SuccessResponse( // Возвращаемый тип в случае успеха
  val message: String
)

@kotlinx.serialization.Serializable
data class ErrorResponse( // Возвращаемый тип в случае ошибки
  val message: String
)

@kotlinx.serialization.Serializable
data class Request(
  val _id: String
)

suspend fun getCurrentNews(context: Context, _id: String) =
  client.safeRequest<SuccessResponse, ErrorResponse>(
    path = "api/keine/news/sort",
    method = HttpMethod.Post,
    body = Request(_id), // Необязательный аргумент
    context = context,
  )

//--- Обращение к этой фукнции ---//
viewModelScope.launch(Dispatchers.IO) {
  val res = getCurrentNews(
    context = context,
    _id = "id"
  )

  if (res is ApiResponse.Success) {
    val success: SuccessResponse = res.data
  } else if (res is ApiResponse.Error) {
    when (res.errorCode) {
      400, 401 -> {
        val error: ErrorResponse = res.errorData
      }
      500 -> {
        val error: ErrorResponse = res.errorData
      }
      else -> {
        val error: ErrorResponse = res.errorData
      }
    }
  }
}
```
