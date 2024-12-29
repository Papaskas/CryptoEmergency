package com.cryptoemergency.cryptoemergency.ui.screens.auth.login

sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()

    data object ContinueAsGuestSuccess : UiState()
    data class ContinueAsGuestError(val message: String?) : UiState()

    data object LoginSuccess : UiState()
    data class LoginError(val message: String?) : UiState()
}