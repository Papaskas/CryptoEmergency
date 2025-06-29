package com.cryptoemergency.cryptoemergency.ui.screens.auth.login

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.makeRequest
import com.papaska.data.qualifiers.StorageQualifiers
import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.useCases.remote.auth.LoginUseCase
import com.papaska.domain.useCases.storage.LocalStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @StorageQualifiers.TokenStorage private val tokenStorage: LocalStorageUseCase<TokenEntity>,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    val currentStep = mutableIntStateOf(0)

    val emailInput = mutableStateOf(TextFieldValue())
    val emailError = mutableStateOf(false)
    val passwordInput = mutableStateOf(TextFieldValue())
    val passwordError = mutableStateOf(false)

    fun continueAsGuest() {
        viewModelScope.launch {
            makeRequest(
                context = context,
                onError = { _, errorMessage ->
                    _uiState.value = UiState.ContinueAsGuestError(errorMessage)
                },
                onSuccess = {
                    viewModelScope.launch {
                        try {
                            val token =
                                it.headers[DomainHttpHeaders.AUTHORIZATION] ?:
                                error(context.resources.getString(R.string.error__internal_server))

                            tokenStorage.put(token.toString())

                            _uiState.value = UiState.ContinueAsGuestSuccess
                        } catch (e: IllegalStateException) {

                            _uiState.value = UiState.ContinueAsGuestError(e.message)
                        }
                    }
                },
            ) {
                _uiState.value = UiState.Loading
                loginUseCase(emailInput.value.text, passwordInput.value.text)
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            makeRequest(
                context = context,
                onError = { _, message ->
                    _uiState.value = UiState.LoginError(message)
                },
                onSuccess = {
                    _uiState.value = UiState.LoginSuccess
                },
            ) {
                _uiState.value = UiState.Loading
                loginUseCase(emailInput.value.text, passwordInput.value.text)
            }
        }
    }
}
