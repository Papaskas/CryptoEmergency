package com.papaska.data.old.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class HttpClientImpl(
    private val httpClient: HttpClient
) : ApiService {
    override suspend fun createPost(name: String) {
        httpClient.get("https://api.example.com/users/$name")
    }
}
