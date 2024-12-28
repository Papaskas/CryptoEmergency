package com.cryptoemergency.cryptoemergency.common

/**
 * Base sealed class for UI states in the application.
 * Defines common states like [Idle], [Success], and [Error].
 */
sealed class BaseUiState {

    /**
     * Represents the initial or idle state.
     */
    data object Idle : BaseUiState()

    /**
     * Represents a successful operation, optionally with a success message.
     *
     * @param message Optional success message.
     */
    data class Success(val message: String? = null) : BaseUiState()

    /**
     * Represents an error state with an error message.
     *
     * @param message Error message.
     */
    data class Error(val message: String) : BaseUiState()
}
