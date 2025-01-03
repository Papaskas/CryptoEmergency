package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.CreatePostViewModel

fun LazyStaggeredGridScope.WarningHeader(
    viewModel: CreatePostViewModel
) {
    item(span = StaggeredGridItemSpan.FullLine) {
        Column {
            Text(
                text = "Вы можете загрузить до 5 фото и до 3 видео", // TODO: перевод
                style = Theme.typography.caption1,
                color = if(viewModel.isError.value) Theme.colors.error else Theme.colors.text4, // TODO: Изменить цвета для light theme
                modifier = Modifier.padding(Theme.dimens.horizontalPadding)
            )

            Spacer(Modifier.height(Theme.dimens.horizontalPadding))

            CommonHorizontalDivider()
        }
    }
}
