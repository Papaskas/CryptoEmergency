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
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    title: String,
) {
    val navController = LocalNavController.current

    Column {
        TopAppBar(
            title = {
                Text(
                    text = title,
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
                        contentDescription = "Лого",
                        tint = Theme.colors.text1,
                    )
                }
            },
        )

        CommonHorizontalDivider()
    }
}