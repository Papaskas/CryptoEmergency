package com.cryptoemergency.cryptoemergency.repository.requests.getPosts

import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val postAll: List<Post>,
)

@Serializable
data class Post(
    val _id: String,
    val userId: User,
    val description: String,
    val media: List<Media>,
    val likes: List<User>,
    val comments: List<Comment>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)

@Serializable
data class User(
    val _id: String,
    val access: List<String>,
    val roles: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)

@Serializable
data class Media(
    val _id: String,
    val type: String,
    val originalUrl: String,
    val resizedUrl: String? = null,
)

@Serializable
data class Comment(
    val _id: String,
    val userId: String,
    val createAt: String,
)
