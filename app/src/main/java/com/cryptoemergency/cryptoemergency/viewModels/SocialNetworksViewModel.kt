package com.cryptoemergency.cryptoemergency.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks.NetworkName
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks.SocialNetworkType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SocialNetworksViewModel @Inject constructor(
//    private val database: AppDatabase,
) : ViewModel() {
    val message = MutableStateFlow<String?>(null)

    var socialNetworks = MutableStateFlow<List<SocialNetworkType>>(emptyList())

    init {
        socialNetworks.value = listOf(
            SocialNetworkType(
                networkName = NetworkName.VK,
                urlPrefix = mutableStateOf(TextFieldValue("https://vk.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.GITHUB,
                urlPrefix = mutableStateOf(TextFieldValue("https://github.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.TIKTOK,
                urlPrefix = mutableStateOf(TextFieldValue("https://tiktok.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.TWITCH,
                urlPrefix = mutableStateOf(TextFieldValue("https://twitch/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.TELEGRAM,
                urlPrefix = mutableStateOf(TextFieldValue("https://t.me/@")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.DISCORD,
                urlPrefix = mutableStateOf(TextFieldValue("https://discord.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.FACEBOOK,
                urlPrefix = mutableStateOf(TextFieldValue("https://facebook.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.INSTAGRAM,
                urlPrefix = mutableStateOf(TextFieldValue("https://instagram.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.LINKEDIN,
                urlPrefix = mutableStateOf(TextFieldValue("https://linkedin.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
            SocialNetworkType(
                networkName = NetworkName.TWITTER,
                urlPrefix = mutableStateOf(TextFieldValue("https://twitter.com/")),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            ),
        )
    }
}
