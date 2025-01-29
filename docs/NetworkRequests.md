> Основной конструктор запросов - `CreateRequest`<br/>
> Он выполняет HTTP-запрос к указанному URL и возвращает результат в виде интерфейса `ApiResponse`<br/>
> Этот интерфейс определяет два возможных результата: успешный `Success` и ошибочный `Error`<br/>
> Также имеется клиент HTTP, настроенный для работы с JSON, логированием и таймаутами - `httpClient`

---

#### Создание запроса:

> Создать функцию подобной этой:

```kotlin
suspend fun getCurrentNews(context: Context, _id: String) =
  httpClient.createRequest<SuccessResponse, ErrorResponse>(
    path = "api/news",
    method = HttpMethod.Post,
    body = Request(_id), // Необязательный аргумент
    context = context,
  )
```

> Обращение к ней

```kotlin
viewModelScope.launch() {
  val res = getCurrentNews(
    context = context,
    _id = "id"
  )

when(res) {
   is ApiResponse.Success -> {
      val success: SuccessResponse = res.data
   }
   is ApiResponse.Error -> {
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

> Вариант с использованием функции `getDefaultMessages`

```kotlin
viewModelScope.launch() {
  val res = getCurrentNews(
    context = context,
    _id = "id"
  )

when(res) {
   is ApiResponse.Success -> {
      val success: SuccessResponse = res.data
   }
   is ApiResponse.Error -> {
      val errorMessage = when (res.status) {
          HttpStatusCode.UnprocessableEntity -> "Такого поста не существует!"

          else -> Http.getDefaultMessages(context, res.status)
      }
   }
}
```

> Возвращаемый тип в случае успеха

```kotlin
@Serializable
data class SuccessResponse(
  val posts: List<Post>,
)

@Serializable
data class Post(
    val title: String,
    val description: String,
)
```

> Возвращаемый тип в случае ошибки

```kotlin
@Serializable
data class ErrorResponse(
  val message: String
)
```

> Необязательное тело запроса

```kotlin
@Serializable
data class Request(
  val _id: String
)
```