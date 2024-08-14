package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.HorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.AboutUser
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.Content
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.Stories

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column {
        Box(modifier = Modifier.padding(Theme.shapes.padding)) {
            AboutUser(viewModel)
        }

        HorizontalDivider()

        Box(modifier = Modifier.padding(Theme.shapes.padding)) {
            Stories(viewModel)
        }

        Content(viewModel)
    }
}
