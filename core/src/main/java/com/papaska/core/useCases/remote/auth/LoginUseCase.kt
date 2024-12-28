package com.papaska.core.useCases.remote.auth

import com.papaska.core.entity.config.ServerConfiguration
import com.papaska.core.http.DomainHttpMethod
import com.papaska.core.repositories.remote.NetworkRepository
import kotlinx.serialization.Serializable

class LoginUseCase (
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
            method = DomainHttpMethod.POST,
            path = "login",
            body = Body(
                email = email,
                password = password,
            ),
            serverConfiguration = serverConfiguration,
            successResponse = SuccessResponse.serializer(),
            errorResponse = ErrorResponse.serializer(),
        )
}
