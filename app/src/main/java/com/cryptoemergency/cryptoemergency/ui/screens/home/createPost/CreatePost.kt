package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.getMediaFiles
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.MediaMenu
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.RatioChanger
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.SelectedMedia
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.Toolbar
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components.WarningHeader

@Composable
fun CreatePost(
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val mediaFiles by getMediaFiles()

    Screen(
        topBar = { ScreenTopBar(
            title = "Создание поста", // TODO: перевод
            actions = { ActionsTopBar() },
            navigationIcon = {},
        ) },
        bottomBar = { BottomBar() },
        horizontalPadding = 0.dp,
        bottomSpacing = 0.dp,
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(it),
            columns = StaggeredGridCells.Fixed(viewModel.columnCount),
            verticalItemSpacing = 1.dp,
            horizontalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            SelectedMedia(viewModel)
            RatioChanger(viewModel)
            Toolbar(viewModel)
            WarningHeader(viewModel)
            MediaMenu(
                viewModel = viewModel,
                mediaFiles = mediaFiles,
            )
        }
    }
}

@Composable
private fun ActionsTopBar() {
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
