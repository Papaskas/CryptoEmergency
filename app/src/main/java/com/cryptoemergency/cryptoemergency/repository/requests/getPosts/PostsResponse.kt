package com.cryptoemergency.cryptoemergency.repository.requests.getPosts

import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val postAll: List<Post>,
)

@Serializable
data class Post(
    val comments: List<Comment>,
    val _id: String,
    val userId: User,
    val description: String,
    val media: List<Media>,
    val createdAt: String,
    val updatedAt: String,
    val likes: List<Like>
)

@Serializable
data class User(
    val _id: String,
    val access: List<Access>,
    val roles: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)

@Serializable
data class Access(
    val access: String,
)

@Serializable
data class Media(
    val enum: List<String>,
    val _id: String,
    val type: String,
    val url: String
)

@Serializable
data class Like(
    val _id: String,
    val userId: String,
    val createAt: String,
)

@Serializable
data class Comment(
    val _id: String,
    val userId: String,
    val createAt: String,
)
