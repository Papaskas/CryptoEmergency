package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.entity

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.papaska.core.entity.db.SocialNetworkName

data class SocialNetworkData(
    val id: Int,
    val networkName: SocialNetworkName,
    val urlPrefix: String,
    val url: MutableState<TextFieldValue>,
    val description: MutableState<TextFieldValue>?,
)