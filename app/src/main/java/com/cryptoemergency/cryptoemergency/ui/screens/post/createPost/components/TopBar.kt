package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar

@Composable
fun TopBar() {
    val navController = LocalNavController.current

    ScreenTopBar(
        title = "Создание поста", // TODO: перевод
        actions = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "Close", // TODO: добавить перевод
                    tint = Theme.colors.text1,
                )
            }
        },
        navigationIcon = {},
    )
}