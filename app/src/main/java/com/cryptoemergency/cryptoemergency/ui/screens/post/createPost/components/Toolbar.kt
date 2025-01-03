package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CameraButton
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.CreatePostViewModel

fun LazyStaggeredGridScope.Toolbar(
    viewModel: CreatePostViewModel
) {
    item(span = StaggeredGridItemSpan.FullLine) {
        Row(
            modifier = Modifier.padding(Theme.dimens.horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SelectAlbums(viewModel)

            Spacer(Modifier.weight(1f))

            MultiselectButton(viewModel)

            Spacer(Modifier.width(10.dp))

            CameraButton { uri ->
                uri?.let {
                    viewModel.mediaFiles.add(0, it)
                }
            }
        }
    }
}

@Composable
private fun SelectAlbums(
    viewModel: CreatePostViewModel
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
}

@Composable
private fun MultiselectButton(
    viewModel: CreatePostViewModel
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(Theme.colors.surface1),
        onClick = {
            viewModel.multipleIsActive.value = !viewModel.multipleIsActive.value
        },
    ) {
        Icon(
            painter = painterResource(R.drawable.multichoice),
            contentDescription = "Мультивыбор", // TODO: перевод
            tint = if(viewModel.multipleIsActive.value) Theme.colors.accent else Theme.colors.text6,
            modifier = Modifier
                .padding(8.dp)
                .size(16.dp),
        )
    }
}
