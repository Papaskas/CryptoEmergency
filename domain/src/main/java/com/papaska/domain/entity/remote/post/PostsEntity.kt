package com.papaska.domain.entity.remote.post

import kotlinx.serialization.Serializable

@Serializable
data class PostsEntity(
    val postAll: List<PostEntity>,
)

@Serializable
data class PostEntity(
    val _id: String,
    val userId: PostUserEntity,
    val description: String,
    val media: List<PostMediaEntity> = emptyList(),
    val likes: List<PostUserEntity> = emptyList(),
    val userComments: List<PostCommentEntity> = emptyList(),
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)

@Serializable
data class PostUserEntity(
    val _id: String,
    val roles: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)

@Serializable
data class PostMediaEntity(
    val _id: String,
    val type: String,
    val originalUrl: String,
    val resizedUrl: String,
)

@Serializable
data class PostCommentEntity(
    val _id: String,
    val userId: String,
    val createAt: String,
)
