package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.HorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.screens.ScrollableScreen
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.AboutUser

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column {
        Box(modifier = Modifier.padding(Theme.shaped.padding)) {
            AboutUser(viewModel)
        }

        HorizontalDivider()
    }
}