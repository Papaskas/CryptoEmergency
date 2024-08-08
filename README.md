# Lint

В проекте установлен detekt (app/config/detekt/detekt.yml)
Для его инициализации в android studio нужно скачать
плагин [detekt](https://plugins.jetbrains.com/plugin/10761-detekt)
Его активация описана в описании плагина

# Theme and colors

* По умолчанию стоит темная тема
* Настройка темы находится в providers/theme/Theme
* Использование цветовой схемы:
    * Text(color = Theme.colors.primary)

# Routing

* Основной и единственный файл роутинга - navigation/Navigation
* Для контроля роутинга необходимо пользоваться методом LocalNavController.current из
  providers/LocalNavController
* Для перехода по страницам нужно использовать не строки, а импортировать обьект Routes из
  NavigationGraph

# TopBar

* Для каждого маршрута указан свой TopBar в файле ui/components/topBar/TopBar.kt
* Если не указан стоит TopBar по умолчанию с пустым заголовком и стрелкой "вернуться назад"

# BottomBar (NavigationBar)

Если для определенного маршрута необходимо убрать/заменить BottomBar, то следует добавить этот
маршрут в логику для ui/components/bottomBar/BottomBar.kt:

```kotlin
@Composable
fun BottomBar() {
    val currentRoute = getCurrentRoute()

    when (currentRoute) {
        Routes.Pages.SplashScreen.route -> EmptyBottomBar()
        Routes.Pages.OnBoardingScreen.route -> EmptyBottomBar()
        Routes.Pages.PinCode.route -> EmptyBottomBar()

        else -> BottomNavigationBar()
    }
}
```

# SharedPreferences || DataStore - ProtoDataStore || LocalStorage

## Для локального хранения используются две библиотеки: DataStore (ключ: значение) и ProtoDataStore (data class, enum class)

* Файл находится в api/store/Store.kt || ProtoStore.kt
* Назначение этих файлов описано в их JavaDoc
* Для использования хранилищей нужно заранее инициализовать ключ в файле repository/store/Keys.kt ||
  ProtoKeys.kt
* Библиотеки являются асинхронными выполняемыми в потоке IO
* Использования хранилища строго в HiltViewModel, иначе не будет работать

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

# Коды ошибок

* 200 - успех
* 201 - успешно создано
* 400 - не понято, синтаксическая ошибка
* 403 Forbidden - нету токена
* 408 Request Timeout - истекло время
* 419 Authentication Timeout - если токен устарел или оказался некорректным
* 422 - понято, но данные неверные
* 500 - ошибка сервера

# SVG

Для использования настоящих файлов svg нужно преобразовывать svg код в compose код на
этом [сайте](https://www.composables.com/svgtocompose)
