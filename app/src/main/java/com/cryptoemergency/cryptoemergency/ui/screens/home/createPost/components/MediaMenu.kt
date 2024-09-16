package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.CreatePostViewModel

@Composable
fun PhotosList(
    viewModel: CreatePostViewModel
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(4),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            WarningHeader()
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            Toolbar()
        }
    }
}

@Composable
private fun WarningHeader() {
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

@Composable
private fun Toolbar() {
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

@Composable
private fun Photos() {

}
