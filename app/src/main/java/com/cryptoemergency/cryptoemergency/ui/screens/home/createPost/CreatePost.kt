package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.CurrentPhoto
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.PhotosList
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.RatioChanger

@Composable
fun CreatePost(
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    Screen(
        topBar = { ScreenTopBar(
            title = "Создание поста", // TODO: перевод
            navigationIcon = { NavigationIcon() }
        ) },
        bottomBar = { BottomBar() },
        horizontalPadding = 0.dp,
        bottomSpacing = 0.dp,
    ) {
        Column(Modifier.padding(it)) {
            CurrentPhoto(viewModel)
            RatioChanger(viewModel)
            PhotosList(viewModel)
        }
    }
}

@Composable
private fun NavigationIcon() {
    val navController = LocalNavController.current

    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            painter = painterResource(R.drawable.close),
            contentDescription = "Close", // TODO: добавить перевод
            tint = Theme.colors.text1,
        )
    }
}

@Composable
private fun BottomBar() {
    Box(Modifier
        .padding(horizontal = Theme.dimens.padding)
        .padding(
            top = Theme.dimens.padding,
            bottom = Theme.dimens.padding * 2,
        )
    ) {
        CommonButton(
            onClick = {},
            text = "Далее", // TODO: перевод
        )
    }
}
