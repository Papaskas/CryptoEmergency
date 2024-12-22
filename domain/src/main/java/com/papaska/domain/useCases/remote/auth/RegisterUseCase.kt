package com.papaska.domain.useCases.remote.auth

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.http.DomainHttpMethod
import com.papaska.domain.repositories.remote.NetworkRepository
import kotlinx.serialization.Serializable

class RegisterUseCase(
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
        val email: String,
        val password: String,
    )

    suspend operator fun invoke(email: String, password: String) =
        networkRepository(
            path = "register",
            method = DomainHttpMethod.POST,
            body = Body(email, password),
            serverConfiguration = serverConfiguration,
            successResponse = SuccessResponse.serializer(),
            errorResponse = ErrorResponse.serializer(),
        )
}