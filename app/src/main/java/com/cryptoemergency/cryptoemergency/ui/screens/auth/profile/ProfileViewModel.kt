package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SocialNetwork(
    @DrawableRes val icon: Int,
    val title: String,
    val type: String?,
    val url: String,
)

data class User(
    val avatarUrl: String,
    val name: String,
    val countSubscribers: Int,
    val countSubscribes: Int,
    val socialNetworks: List<SocialNetwork>,
    val username: String,
    val statusText: String,
    val specialization: String,
    val birthday: String,
    val language: String,
    val dateOfRegistration: String,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {
    val user = User(
        avatarUrl = "https://example.com/avatar.jpg",
        name = "Romanov Alex",
        countSubscribers = 1000,
        countSubscribes = 500,
        socialNetworks = listOf(
            SocialNetwork(R.drawable.facebook, "Facebook", "","https://www.facebook.com/user"),
            SocialNetwork(R.drawable.instagram, "Instagram", "", "https://www.instagram.com/user"),
            SocialNetwork(R.drawable.twitter, "Twitter", "", "https://www.twitter.com/user"),
        ),
        username = "@kriptoinvestor",
        statusText = "Активный криптоэнтузиаст, постоянно исследующий новые технологии блокчейна",
        specialization = "Криптоинвестор",
        birthday = "12 августа 2000 г.",
        language = "Русский",
        dateOfRegistration = "9 июля 2020 г.",
    )
}
