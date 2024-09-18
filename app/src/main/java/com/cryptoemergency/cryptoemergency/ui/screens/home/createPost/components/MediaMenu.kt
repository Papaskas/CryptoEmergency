package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.CreatePostViewModel

fun LazyStaggeredGridScope.MediaMenu(
    viewModel: CreatePostViewModel,
    mediaFiles: List<Uri>,
) {
    WarningHeader()
    Toolbar()
    MediaFiles(
        viewModel = viewModel,
        files = mediaFiles,
    )
}

private fun LazyStaggeredGridScope.WarningHeader() {
    item(span = StaggeredGridItemSpan.FullLine) {
        Column {
            Text(
                text = "Вы можете загрузить до 5 фото и до 3 видео", // TODO: перевод
                style = Theme.typography.caption1,
                color = Theme.colors.text4, // TODO: Изменить цвета для light theme
                modifier = Modifier.padding(Theme.dimens.padding)
            )

            Spacer(Modifier.height(Theme.dimens.padding))

            CommonHorizontalDivider()
        }
    }
}

private fun LazyStaggeredGridScope.Toolbar() {
    item(span = StaggeredGridItemSpan.FullLine) {
        Row(
            modifier = Modifier.padding(Theme.dimens.padding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Все фото", // TODO: перевод
                    style = Theme.typography.h3,
                    color = Theme.colors.text1, // TODO: Изменить цвета для light theme
                )
                Spacer(Modifier.width(5.dp))
                Icon(
                    painter = painterResource(R.drawable.arrow_down),
                    tint = Theme.colors.text6,
                    contentDescription = null,
                )
            }

            Spacer(Modifier.weight(1f))

            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Theme.colors.surface1),
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(R.drawable.multichoice),
                    contentDescription = "Мультивыбор", // TODO: перевод
                    tint = Theme.colors.text6,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(16.dp),
                )
            }

            Spacer(Modifier.width(10.dp))

            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Theme.colors.surface1),
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Сделать фото", // TODO: перевод
                    tint = Theme.colors.text6,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(16.dp)
                )
            }
        }
    }
}

private fun LazyStaggeredGridScope.MediaFiles(
    viewModel: CreatePostViewModel,
    files: List<Uri>,
) {
    items(files.size) {
        MediaItem(
            file = files[it],
            index = it,
            viewModel = viewModel,
        )
    }
}

@Composable
private fun MediaItem(
    file: Uri,
    index: Int,
    viewModel: CreatePostViewModel,
) {
    val size = (LocalConfiguration.current.screenWidthDp / viewModel.columnCount).dp
    var isSelected by remember { mutableStateOf(false) }

    Box(
        Modifier
            .size(size)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        if(isSelected) {
                            viewModel.selectionMedia.remove(file)
                            isSelected = false
                        } else {
                            viewModel.selectionMedia.add(file)
                            viewModel.multipleSelectionIsActive.value = true
                            isSelected = true
                        }
                    }
                )
            },
    ) {
        MediaSelection(
            isSelected = isSelected,
            multipleSelectionIsActive = viewModel.multipleSelectionIsActive.value,
            index = index,
        )

        AsyncImage(
            model = file,
            placeholder = painterResource(R.drawable.banner1), // TODO: добавить placeholder
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun BoxScope.MediaSelection(
    isSelected: Boolean,
    multipleSelectionIsActive: Boolean,
    index: Int,
) {
    if(isSelected) {
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
    } else if(multipleSelectionIsActive) {
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
