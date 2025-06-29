package com.papaska.domain.useCases.remote.post

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.entity.remote.post.PostsEntity
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.repositories.remote.NetworkRepository
import kotlinx.serialization.Serializable

class GetPostUseCase(
    private val networkRepository: NetworkRepository,
    private val serverConfiguration: ServerConfiguration,
) {
    @Serializable
    data class ErrorResponse(
        val message: String
    )

    suspend operator fun invoke() =
        networkRepository.invoke(
            method = DomainHttpMethod.GET,
            path = "posts",
            successResponse = PostsEntity.serializer(),
            errorResponse = ErrorResponse.serializer(),
            serverConfiguration = serverConfiguration,
        )
}