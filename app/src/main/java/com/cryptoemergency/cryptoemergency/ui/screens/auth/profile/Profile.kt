package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ProfileTopBar
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.sections.AboutUser
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.sections.Content
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.sections.Stories

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Screen(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        padding = PaddingValues(0.dp),
        bottomSpacing = 0.dp,
        topBar = { ProfileTopBar() },
    ) {
        Box(modifier = Modifier.padding(Theme.values.padding)) {
            AboutUser(viewModel)
        }

        CommonHorizontalDivider()

        Box(modifier = Modifier.padding(Theme.values.padding)) {
            Stories(viewModel)
        }

        Content(viewModel)
    }
}
