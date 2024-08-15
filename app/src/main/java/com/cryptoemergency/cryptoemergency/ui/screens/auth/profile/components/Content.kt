package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonTabs
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileViewModel

@Composable
fun Content(viewModel: ProfileViewModel) {
    Box(
        Modifier
            .background(
                color = Theme.colors.surface,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )
            .fillMaxSize()
            .padding(vertical = Theme.shapes.padding),
    ) {
        Tabs()
    }
}

@Composable
private fun Tabs() {
    val titles = arrayOf("Моя лента", "Социальные сети")
    val content = arrayOf<@Composable () -> Unit>({ NewsFeed() }, { SocialNetworks() })

    CommonTabs(
        tabTitles = titles,
        content = content,
    )
}
