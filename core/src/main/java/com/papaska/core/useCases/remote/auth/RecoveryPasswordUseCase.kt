package com.papaska.core.useCases.remote.auth

import com.papaska.core.entity.config.ServerConfiguration
import com.papaska.core.entity.http.DomainHttpMethod
import com.papaska.core.repositories.remote.NetworkRepository
import kotlinx.serialization.Serializable

/**
 * Этот UseCase не готов
 * */
class RecoveryPasswordUseCase(
    private val networkRepository: NetworkRepository,
    private val serverConfiguration: ServerConfiguration,
) {
    @Serializable
    data class SuccessResponse(
        val message: String
    )

    @Serializable
    data class ErrorResponse(
        val message: String
    )

    @Serializable
    data class Body(
        val email: String
    )

    suspend operator fun invoke() =
        networkRepository(
            method = DomainHttpMethod.POST,
            path = "password-recovery",
            successResponse = SuccessResponse.serializer(),
            errorResponse = ErrorResponse.serializer(),
            serverConfiguration = serverConfiguration,
        )
}