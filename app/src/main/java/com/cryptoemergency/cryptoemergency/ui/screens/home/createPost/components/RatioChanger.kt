package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.PhotoFormat

fun LazyStaggeredGridScope.RatioChanger(viewModel: CreatePostViewModel) {
    val ratioOptions = listOf(PhotoFormat.RATIO_1X1, PhotoFormat.RATIO_3X4, PhotoFormat.RATIO_16X9)

    item(span = StaggeredGridItemSpan.FullLine) {
        Row(Modifier.padding(Theme.dimens.padding)) {
            ratioOptions.forEach { photoFormat ->
                val selected = viewModel.selectedRatioOption.value == photoFormat

                Surface(
                    shape = CircleShape,
                    color = if (selected) Theme.colors.background3 else Color.Transparent,
                    contentColor = Theme.colors.text1,
                    modifier = Modifier
                        .clip(CircleShape)
                        .selectable(
                            selected = selected,
                            onClick = { viewModel.selectedRatioOption.value = photoFormat },
                            interactionSource = null,
                            indication = ripple(),
                        )
                ) {
                    Text(
                        text = photoFormat.title,
                        style = Theme.typography.h4,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
                    )
                }

                Spacer(Modifier.width(Theme.dimens.padding))
            }
        }
    }
}
