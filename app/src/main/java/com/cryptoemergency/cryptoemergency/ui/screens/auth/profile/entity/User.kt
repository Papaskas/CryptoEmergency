package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.entity

data class User(
    val avatarUrl: String,
    val name: String,
    val countSubscriptions: Int,
    val countSubscribers: Int,
    val username: String,
    val statusText: String,
    val specialization: String,
    val birthday: String,
    val language: String,
    val dateOfRegistration: String,
)