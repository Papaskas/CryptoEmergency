package com.cryptoemergency.cryptoemergency

import com.cryptoemergency.cryptoemergency.navigation.Destination

sealed class UiState(
    open val isLoading: Boolean = false,
    open val message: String? = null,
    open val redirect: Destination? = null,
) {

    data class Idle(
        override val isLoading: Boolean = false,
        override val message: String? = null,
        override val redirect: Destination? = null,
    ) : UiState()

    data class Success(
        override val isLoading: Boolean = false,
        override val message: String? = null,
        override val redirect: Destination? = null,
    ) : UiState()

    data class Error(
        override val isLoading: Boolean = false,
        override val message: String? = null,
        override val redirect: Destination? = null,
    ) : UiState()
}