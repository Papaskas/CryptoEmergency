package com.papaska.domain.useCases.remote.useCases.createPost

class CreatePostUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() {

    }
}

suspend fun createPostRequest(context: Context, body: Request) =
    httpClient.createRequest<Response, ErrorResponse>(
        path = "$PATH/posts/create",
        method = HttpMethod.Post,
        context = context,
        body = body,
    )
