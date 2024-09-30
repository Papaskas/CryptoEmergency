package com.cryptoemergency.cryptoemergency.ui.screens.pages.login

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.api.http.ApiResponse
import com.cryptoemergency.cryptoemergency.api.store.Store
import com.cryptoemergency.cryptoemergency.lib.Http
import com.cryptoemergency.cryptoemergency.lib.Redirect
import com.cryptoemergency.cryptoemergency.module.TokenStore
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.repository.requests.getToken.getTokenRequest
import com.cryptoemergency.cryptoemergency.repository.requests.login.loginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            isLoading.value = true
            val token = getTokenRequest(context)

            if (token is ApiResponse.Success) {
                isLoading.value = false

                try {
                    tokenStore.put(
                        token.headers["authorization"] ?:
                        error(context.resources.getString(R.string.error__internal_server))
                    )
                } catch (e: IllegalStateException) {
                    message.value = e.message
                }
            } else if (token is ApiResponse.Error) {
                isLoading.value = false

                message.value = Http.getDefaultMessages(context, token.status)
            }
        }
    }

    fun login(navController: NavController) {
        viewModelScope.launch {
            isLoading.value = true

            val res = loginRequest(context, emailInput.value.text, passwordInput.value.text)

            if (res is ApiResponse.Success) {
                isLoading.value = false

                message.value = "Успешная авторизация!"
                redirect.value = Redirect(Destination.Home.Home)
            } else if (res is ApiResponse.Error) {
                isLoading.value = false

                message.value = Http.getDefaultMessages(context, res.status)
            }
        }
    }
}
