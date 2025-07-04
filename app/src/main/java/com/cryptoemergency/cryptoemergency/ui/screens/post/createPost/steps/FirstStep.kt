package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.lib.Media
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.MediaMenu
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.RatioChanger
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.SelectedMedia
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.Toolbar
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.TopBar
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.WarningHeader

@Composable
fun FirstStep(
    viewModel: CreatePostViewModel,
) {
    viewModel.mediaFiles.addAll(Media.getImages().value)

    Screen(
        topBar = { TopBar() },
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
            MediaMenu(viewModel)
        }
    }
}

@Composable
private fun BottomBar(
    viewModel: CreatePostViewModel,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = Theme.dimens.horizontalPadding)
            .padding(
                top = Theme.dimens.horizontalPadding,
                bottom = Theme.dimens.horizontalPadding * 2,
            )
    ) {
        CommonButton(
            onClick = { viewModel.currentStep.intValue = 1 },
            text = "Далее", // TODO: перевод
            isEnabled = viewModel.selectedMedia.isNotEmpty(),
        )
    }
}
