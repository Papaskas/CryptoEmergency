package com.cryptoemergency.cryptoemergency.ui.common.topBar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import com.cryptoemergency.cryptoemergency.ui.common.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.providers.theme.currentTheme
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    val navController = LocalNavController.current

    Column {
        TopAppBar(
            title = {},
            modifier = Modifier.padding(start = Theme.shaped.padding, end = 10.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                Icon(
                    painter = if(currentTheme == CurrentTheme.DARK) {
                        painterResource(R.drawable.logo__light)
                    } else {
                        painterResource(R.drawable.logo__dark)
                    },
                    contentDescription = "Лого",
                    tint = Color.Unspecified,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Home.Home)
                    }
                )
            },
            actions = {
                IconButton(onClick = {
                    navController.navigate(Routes.Auth.Profile)
                }) {
                    Icon(
                        painterResource(R.drawable.notification),
                        contentDescription = "Уведомления",
                        tint = Color.Unspecified,
                    )
                }
                IconButton(onClick = {
                    navController.navigate(Routes.Auth.Profile)
                }) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.avatar_placeholder),
                        contentDescription = "Профиль",
                        tint = Color.Unspecified,
                    )
                }
            }
        )

        HorizontalDivider()
    }
}
