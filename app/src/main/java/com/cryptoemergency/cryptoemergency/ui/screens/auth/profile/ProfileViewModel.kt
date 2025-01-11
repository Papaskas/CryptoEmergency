package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile

import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {
    val user = User(
        avatarUrl = "https://example.com/avatar.jpg",
        name = "Romanov Alex",
        countSubscriptions = 1000,
        countSubscribers = 500,
        username = "@kriptoinvestor",
        statusText = "Активный криптоэнтузиаст, постоянно исследующий новые технологии блокчейна",
        specialization = "Криптоинвестор",
        birthday = "12 августа 2000 г.",
        language = "Русский",
        dateOfRegistration = "9 июля 2020 г.",
    )
}
