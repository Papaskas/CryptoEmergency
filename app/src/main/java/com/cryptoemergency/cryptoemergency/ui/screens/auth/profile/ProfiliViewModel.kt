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
    val statusText: String,
    val countSubscribers: Int,
    val countSubscribes: Int,
    val socialNetworks: List<SocialNetwork>,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {
    val user = User(
        avatarUrl = "https://example.com/avatar.jpg",
        name = "Romanov Alex",
        statusText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        countSubscribers = 1000,
        countSubscribes = 500,
        socialNetworks = listOf(
            SocialNetwork(R.drawable.academy__filled, "Facebook", "","https://www.facebook.com/user"),
            SocialNetwork(R.drawable.academy__filled, "Instagram", "", "https://www.instagram.com/user"),
            SocialNetwork(R.drawable.academy__filled, "Twitter", "", "https://www.twitter.com/user"),
        ),
    )
}
