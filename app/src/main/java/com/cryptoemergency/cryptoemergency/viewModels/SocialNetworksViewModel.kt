package com.cryptoemergency.cryptoemergency.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworks.SocialNetworkType
import com.papaska.core.entity.db.SocialNetworkName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SocialNetworksViewModel @Inject constructor(

) : ViewModel() {
    val socialNetworks = MutableStateFlow<List<SocialNetworkType>>(emptyList())

    init {
        socialNetworks.value = listOf(
            SocialNetworkType(
                networkName = SocialNetworkName.VK,
                urlPrefix = mutableStateOf(TextFieldValue("https://vk.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.GITHUB,
                urlPrefix = mutableStateOf(TextFieldValue("https://github.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.TIKTOK,
                urlPrefix = mutableStateOf(TextFieldValue("https://tiktok.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.TWITCH,
                urlPrefix = mutableStateOf(TextFieldValue("https://twitch/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.TELEGRAM,
                urlPrefix = mutableStateOf(TextFieldValue("https://t.me/@")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.DISCORD,
                urlPrefix = mutableStateOf(TextFieldValue("https://discord.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.FACEBOOK,
                urlPrefix = mutableStateOf(TextFieldValue("https://facebook.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.INSTAGRAM,
                urlPrefix = mutableStateOf(TextFieldValue("https://instagram.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.LINKEDIN,
                urlPrefix = mutableStateOf(TextFieldValue("https://linkedin.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = SocialNetworkName.TWITTER,
                urlPrefix = mutableStateOf(TextFieldValue("https://twitter.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
        )
    }
}
