package com.papaska.data.infrastructure.remote.apiNetwork

import kotlinx.serialization.Serializable

private typealias Args = HashMap<String, String>
private typealias Headers = HashMap<String, String>
private typealias Data = HashMap<String, String>
private typealias Form = HashMap<String, String>
private typealias Files = HashMap<String, String>
private typealias Json = HashMap<String, String>?

internal object MockResponse {

    @Serializable
    data class SuccessResponsePost(
        val args: Args,
        val data: Data,
        val files: Files,
        val form: Form,
        val headers: Headers,
        val json: Json,
        val url: String,
    )

    @Serializable
    data class ErrorPlaceholder(val message: String)
}