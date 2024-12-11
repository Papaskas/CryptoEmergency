package com.cryptoemergency.cryptoemergency.api.domain.model.store.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val _id: String = "",
    val username: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val city: String = "",
    val roles: Array<String> = arrayOf(""),
)
