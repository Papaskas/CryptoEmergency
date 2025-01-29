### Для локального хранения используются две [библиотеки](https://developer.android.com/topic/libraries/architecture/datastore) - `DataStore` (ключ: значение) и `ProtoDataStore` (`data class`, `enum class`)

> Реализации этих библиотек интегрированы в проект с помощью [hilt](https://dagger.dev/hilt) >> `module/(Proto)StoreModule.kt`<br/>
> Они работают в `IO` потоке

---

### Как они реализованы в проекте

> Обе библиотеки реализуют абстрактный класс `StorageRepository`, который затем указывается в `hilt`<br/>
> На вход они ожидают `Context` для доступа к хранилищу и `sealed class (Proto)Keys.kt`

---

### Что такое ключ

> Ключ - представление в виде ключей, используемых для хранения и извлечения данных в хранилище.<br/>
> Каждый ключ связан с определенным типом данных, значением по умолчанию и сериализатором (Сериализатор только для `ProtoStore`).

> Ключи находятся в `repository/store/(Proto)Keys.kt`<br/>
> В этой папке есть файл `GenericSerializer` - нужен только для сериализации `Proto` данных (`data class`, `enum class`)<br/>
> Также есть папка `data` - интерфейсы хранимых данных в `ProtoStore`

---

#### Пример ключа в случае обычного `Store`(ключ: значение) - дополнительная документация о нем описана в JavaDoc в `store/Keys.kt`<br/>

```kotlin
// Коллекция обычных ключей
sealed class Keys<T>( 
    val key: Preferences.Key<T>,
    val defaultValue: T,
) {
    data object TOKEN : Keys<String>(
        key = stringPreferencesKey("TOKEN"), // Имя ключа хранимого в файле
        defaultValue = "", // Значение по умолчанию
    ) 
}
```
---

#### Пример ключа в случае `ProtoStore`(`data class`, `enum class`) - дополнительная документация о нем описана в JavaDoc в `store/ProtoKeys.kt`

```kotlin
sealed class ProtoKeys<T>(
  val key: Preferences.Key<String>,
  val defaultValue: T,
  val serializer: Serializer<T>,
) {
  data object USER : ProtoKeys<User>(
    key = stringPreferencesKey("user"), // Имя ключа хранимого в файле
    defaultValue = User(),
    serializer = GenericSerializer(serializer<User>(), User()), // Сериализатор прописывается один раз
  )

  data object THEME : ProtoKeys<CurrentTheme>(
    key = stringPreferencesKey("theme"), // Имя ключа хранимого в файле
    defaultValue = CurrentTheme.NULL,
    serializer = GenericSerializer(serializer<CurrentTheme>(), CurrentTheme.NULL), // Сериализатор прописывается один раз
  )
}
```
```kotlin
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

---

### Использование

1. Во `ViewModel` получить доступ к хранилищу
2. Использовать методы хранилища
```kotlin
@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeStore: StorageRepository<CurrentTheme>, // 1 Получил экземляр хранилища, context и ключ ему передал hilt, о работе hilt смотри оф. доку
    @TokenStore private val tokenStore: StorageRepository<String>, // 1 Смотри назначение Qualifier в оф. документации hilt
) : ViewModel() {
    
    fun fetchTheme() {
        viewModelScope.launch {
            val currentTheme: CurrentTheme = themeStore.get() // 2 Использование встроенных методов 
        }
    }
}
```

---

### Создание новых данных

> Создать нужный ключ в `(Proto)Keys.kt`<br/>
> Добавить новый ключ в `module/(Proto)StoreModule.kt`, согласно документации [Hilt](https://dagger.dev/hilt)