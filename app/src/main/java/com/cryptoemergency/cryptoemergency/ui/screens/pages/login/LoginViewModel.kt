package com.cryptoemergency.cryptoemergency.ui.screens.pages.login

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.api.store.Store
import com.cryptoemergency.cryptoemergency.lib.Redirect
import com.cryptoemergency.cryptoemergency.lib.makeRequest
import com.cryptoemergency.cryptoemergency.module.TokenStore
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.repository.requests.login.loginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @TokenStore private val tokenStore: Store<String>,
) : ViewModel() {
    val currentStep = mutableIntStateOf(0)

    val message = MutableStateFlow<String?>(null)
    val redirect = MutableStateFlow<Redirect?>(null)
    val isLoading = MutableStateFlow(false)

    val emailInput = mutableStateOf(TextFieldValue())
    val emailError = mutableStateOf(false)
    val passwordInput = mutableStateOf(TextFieldValue())
    val passwordError = mutableStateOf(false)

    fun continueAsGuest() {
        viewModelScope.launch {
            makeRequest(
                context = context,
                isLoading = isLoading,
                message = message,
                onRequest = {
                    loginRequest(context, emailInput.value.text, passwordInput.value.text)
                },
            ) {
                try {
                    tokenStore.put(
                        it.headers["authorization"] ?:
                        error(context.resources.getString(R.string.error__internal_server))
                    )
                } catch (e: IllegalStateException) {
                    message.value = e.message
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            makeRequest(
                context = context,
                isLoading = isLoading,
                message = message,
                onRequest = {
                    loginRequest(context, emailInput.value.text, passwordInput.value.text)
                },
                errors = mapOf(
                    HttpStatusCode.OK to ""
                )
            ) {
                message.value = "Успешная авторизация!"
                redirect.value = Redirect(Destination.Home.Home)
            }
        }
    }
}
