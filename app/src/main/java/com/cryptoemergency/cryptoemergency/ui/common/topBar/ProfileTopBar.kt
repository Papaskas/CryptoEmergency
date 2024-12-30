package com.cryptoemergency.cryptoemergency.ui.common.topBar

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.buttons.switchTheme.SwitchThemeButton
import com.papaska.core.entity.local.ThemeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar() {
    val navController = LocalNavController.current
    val res = LocalContext.current.resources

    Column {
        TopAppBar(
            title = {
                Text(
                    text = res.getString(R.string.my_profile),
                    style = Theme.typography.h3,
                    color = Theme.colors.text1,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Theme.colors.backgroundMain,
            ),
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = res.getString(R.string.back),
                        tint = Theme.colors.text6,
                    )
                }
            },
            actions = {
                ActionSwitchTheme()
                IconButton(onClick = {
                    navController.navigate(Destination.Auth.ChangeProfileData)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.edit),
                        contentDescription = res.getString(R.string.change_profile),
                        tint = Theme.colors.text6,
                    )
                }
                IconButton(onClick = { /**/ }) {
                    Icon(
                        painterResource(R.drawable.more__outline),
                        contentDescription = res.getString(R.string.more),
                        tint = Theme.colors.text6,
                    )
                }
            }
        )

        CommonHorizontalDivider()
    }
}

@Composable
private fun ActionSwitchTheme() {
    val res = LocalContext.current.resources
    val localTheme = LocalTheme.current

    val theme = when(localTheme) {
        ThemeEntity.DARK -> ThemeEntity.LIGHT
        ThemeEntity.LIGHT -> ThemeEntity.DARK
        ThemeEntity.NULL -> ThemeEntity.LIGHT
    }

    SwitchThemeButton(themeEntity = theme) {
        IconButton(onClick = {
            Log.d("theme", theme.name)

            it(theme)
        }) {
            Icon(
                painterResource(Theme.icons.theme),
                contentDescription = res.getString(R.string.change_theme),
                tint = Theme.colors.text6,
            )
        }
    }
}