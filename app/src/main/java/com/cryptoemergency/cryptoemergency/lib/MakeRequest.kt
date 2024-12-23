package com.cryptoemergency.cryptoemergency.lib

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.http.DomainHttpStatusCode
import com.papaska.domain.useCases.remote.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Отправляет запрос API и обрабатывает ответ.
 *
 * @param context [Context] Необходим для получения сообщения ошибки по умолчанию
 * @param errors [Map] Список пользовательских сообщений для определенных статусов
 * Если статус не найден, будет использовано сообщение по умолчанию
 * @param onSuccess Коллбэк при успешном ответе
 * @param onError Коллбэк при ошибочном ответе
 * @param onRequest Функция самого запроса, которая отправляет запрос API и возвращает ответ API
 *
 * @param Success Тип успешного ответа
 * @param Error Тип ответа с ошибкой
 *
 * @sample SampleViewModel
 */
suspend fun <Success, Error> makeRequest(
    context: Context,
    errors: Map<DomainHttpStatusCode, String> = emptyMap(),
    onSuccess: (ApiResponse.Success<Success>) -> Unit,
    onError: (ApiResponse.Error<Error>, String?) -> Unit,
    onRequest: suspend () -> ApiResponse<out Success, out Error>,
) = withContext(Dispatchers.IO) {
    val res = onRequest()

    when (res) {
        is ApiResponse.Success -> {
            onSuccess(res as ApiResponse.Success<Success>)
        }
        is ApiResponse.Error -> {
            val errorMessage = errors[res.status] ?: Http.getDefaultMessages(context, res.status)

            onError(res as ApiResponse.Error<Error>, errorMessage)
        }
    }
}

@HiltViewModel
private class SampleViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val loginUseCase: LoginUseCase,
): ViewModel() {
    val redirect = MutableStateFlow<Redirect?>(null)
    val message = MutableStateFlow<String?>(null)

    fun sampleRequest () {
        viewModelScope.launch {
            makeRequest(
                context = context,
                errors = mapOf(
                    DomainHttpStatusCode.Conflict to "Conflict message",
                    DomainHttpStatusCode.BadRequest to "Bad request",
                    DomainHttpStatusCode.NoContent to "No content",
                ),
                onSuccess = {
                    redirect.value = Redirect(Destination.Home.Home)
                },
                onError = { res, errorMessage ->
                    message.value = errorMessage
                },
            ) {
                loginUseCase("mail@mail.ru", "password!@A4")
            }
        }
    }
}
