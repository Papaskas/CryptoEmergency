package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.screens.post.CreatePostViewModel

fun LazyStaggeredGridScope.MediaMenu(
    viewModel: CreatePostViewModel,
) {
    items(viewModel.mediaFiles.size) {
        MediaItem(
            file = viewModel.mediaFiles[it],
            viewModel = viewModel,
        )
    }
}

@Composable
private fun MediaItem(
    file: Uri,
    viewModel: CreatePostViewModel,
) {
    val size = (LocalConfiguration.current.screenWidthDp / viewModel.columnCount).dp

    Box(
        Modifier
            .size(size)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { viewModel.toggleMultipleMedia(file) },
                    onTap = { viewModel.toggleMedia(file) }
                )
            },
    ) {
        SelectorIndicator(
            isSelected = viewModel.selectedMedia.contains(file),
            multipleIsActive = viewModel.multipleIsActive.value,
            index = viewModel.selectedMedia.indexOf(file)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(remember(file) { file })
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun BoxScope.SelectorIndicator(
    isSelected: Boolean,
    multipleIsActive: Boolean,
    index: Int,
) {
    if(multipleIsActive && isSelected) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .size(20.dp)
                .zIndex(1f)
                .clip(CircleShape)
                .background(Theme.colors.accent)
                .border(1.dp, Theme.colors.text6, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = (index + 1).toString(),
                style = Theme.typography.caption1,
                color = Theme.colors.text6,
            )
        }
    } else if(multipleIsActive) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .size(20.dp)
                .zIndex(1f)
                .clip(CircleShape)
                .background(Theme.colors.text6.copy(alpha = .4f))
                .border(1.dp, Theme.colors.text6, CircleShape)
        )
    }
}
