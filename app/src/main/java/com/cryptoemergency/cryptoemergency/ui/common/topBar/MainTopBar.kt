package com.cryptoemergency.cryptoemergency.ui.common.topBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current

    Column(modifier) {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Theme.colors.backgroundMain,
            ),
            navigationIcon = {
                Row {
                    Spacer(Modifier.width(Theme.dimens.padding))
                    Icon(
                        painter = painterResource(Theme.icons.logo),
                        contentDescription = "Лого", // TODO: translate
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            navController.navigate(Routes.Home.Home)
                        }
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    navController.navigate(Routes.Page.Chat)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.chat__filled),
                        contentDescription = "Чат", // TODO: translate
                        tint = Color.Unspecified,
                    )
                }

                IconButton(onClick = { /**/ }) {
                    Icon(
                        painterResource(R.drawable.notification),
                        contentDescription = "Уведомления", // TODO: translate
                        tint = Theme.colors.text6,
                    )
                }

                Spacer(Modifier.width(5.dp))
                IconButton(onClick = {
                    navController.navigate(Routes.Auth.Profile)
                }) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.avatar_placeholder),
                        contentDescription = "Профиль", // TODO: translate
                        tint = Color.Unspecified,
                    )
                }
                Spacer(Modifier.width(10.dp))
            }
        )

        CommonHorizontalDivider()
    }
}
