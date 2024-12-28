package com.papaska.core.useCases.remote.token

import com.papaska.core.entity.config.ServerConfiguration
import com.papaska.core.http.DomainHttpMethod
import com.papaska.core.repositories.remote.NetworkRepository
import kotlinx.serialization.Serializable

class InitTokenUseCase(
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

    suspend operator fun invoke() =
        networkRepository(
            method = DomainHttpMethod.POST,
            path = "guest",
            successResponse = SuccessResponse.serializer(),
            errorResponse = ErrorResponse.serializer(),
            serverConfiguration = serverConfiguration,
        )
}
