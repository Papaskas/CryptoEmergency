package com.papaska.domain.entity.local

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val _id: String = "",
    val username: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val city: String = "",
    val roles: Array<String> = arrayOf(""),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (_id != other._id) return false
        if (username != other.username) return false
        if (surname != other.surname) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        if (city != other.city) return false
        if (!roles.contentEquals(other.roles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + roles.contentHashCode()
        return result
    }
}