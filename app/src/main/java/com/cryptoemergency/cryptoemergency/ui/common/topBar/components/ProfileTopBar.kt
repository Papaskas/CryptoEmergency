package com.cryptoemergency.cryptoemergency.ui.common.topBar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.viewModels.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Мой профиль",
                    style = Theme.typography.h3,
                    color = Theme.colors.text1,
                )
            },
            modifier = Modifier.padding(start = Theme.shapes.padding, end = 10.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "Назад",
                    tint = Theme.colors.text6,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Spacer(Modifier.width(15.dp))
            },
            actions = {
                IconButton(onClick = { themeViewModel.toggleTheme() }) {
                    Icon(
                        painterResource(Theme.icons.theme),
                        contentDescription = "Сменить тему приложения",
                        tint = Theme.colors.text6,
                    )
                }
                IconButton(onClick = { /**/ }) {
                    Icon(
                        painter = painterResource(R.drawable.edit),
                        contentDescription = "Настройки",
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
