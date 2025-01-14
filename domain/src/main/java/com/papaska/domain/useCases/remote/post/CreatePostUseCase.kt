package com.papaska.domain.useCases.remote.post

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.repositories.remote.NetworkRepository
import kotlinx.serialization.Serializable

class CreatePostUseCase(
    private val networkRepository: NetworkRepository,
    private val serverConfiguration: ServerConfiguration,
) {

    @Serializable
    data class Post(
        val description: String,
        val media: List<Media>
    )

    @Serializable
    data class Media(
        val type: String,
        val originalUrl: String,
    )

    @Serializable
    data object SuccessResponse

    @Serializable
    data object ErrorResponse

    suspend operator fun invoke(post: Post) =
        networkRepository.invoke(
            method = DomainHttpMethod.POST,
            path = "posts/create",
            body = post,
            successResponse = SuccessResponse.serializer(),
            errorResponse = ErrorResponse.serializer(),
            serverConfiguration = serverConfiguration,
        )
}