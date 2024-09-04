package com.cryptoemergency.cryptoemergency.ui.common.topBar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.viewModels.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val locale = LocalLocale.current

    Column {
        TopAppBar(
            title = {
                Text(
                    text = locale.titles.myProfile,
                    style = Theme.typography.h3,
                    color = Theme.colors.text1,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = "Назад",
                        tint = Theme.colors.text6,
                    )
                }
            },
            actions = {
                IconButton(onClick = { themeViewModel.toggleTheme() }) {
                    Icon(
                        painterResource(Theme.icons.theme),
                        contentDescription = "Сменить тему приложения",
                        tint = Theme.colors.text6,
                    )
                }
                IconButton(onClick = {
                    navController.navigate(Routes.Auth.ChangeProfileData)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.edit),
                        contentDescription = "Изменить данные профиля",
                        tint = Theme.colors.text6,
                    )
                }
                IconButton(onClick = { /**/ }) {
                    Icon(
                        painterResource(R.drawable.more__outline),
                        contentDescription = "Еще",
                        tint = Theme.colors.text6,
                    )
                }
            }
        )

        CommonHorizontalDivider()
    }
}