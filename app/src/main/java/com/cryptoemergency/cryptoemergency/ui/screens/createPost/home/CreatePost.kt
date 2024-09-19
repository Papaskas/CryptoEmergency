package com.cryptoemergency.cryptoemergency.ui.screens.createPost.home

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
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.components.MediaMenu
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.components.RatioChanger
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.components.SelectedMedia
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.components.Toolbar
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.components.WarningHeader

@Composable
fun CreatePostScreen(viewModel: CreatePostViewModel) {
    val mediaFiles by getMediaFiles()

    Screen(
        topBar = { ScreenTopBar(
            title = "Создание поста", // TODO: перевод
            actions = { ActionsTopBar() },
            navigationIcon = {},
        ) },
        bottomBar = { BottomBar(viewModel) },
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
private fun BottomBar(
    viewModel: CreatePostViewModel
) {
    val navController = LocalNavController.current

    Box(
        modifier = Modifier
            .padding(horizontal = Theme.dimens.padding)
            .padding(
                top = Theme.dimens.padding,
                bottom = Theme.dimens.padding * 2,
            )
    ) {
        CommonButton(
            onClick = {
                navController.navigate(Routes.CreatePost.ModifyPost(
                    viewModel.selectedMedia.toList().map {
                        it.toString()
                    }
                ))
            },
            text = "Далее", // TODO: перевод
        )
    }
}
