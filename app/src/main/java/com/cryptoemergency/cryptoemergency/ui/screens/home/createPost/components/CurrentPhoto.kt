package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.screens.home.createPost.CreatePostViewModel

fun LazyStaggeredGridScope.CurrentPhoto(viewModel: CreatePostViewModel) {
    item(span = StaggeredGridItemSpan.FullLine) {
        Box(
            modifier = Modifier
                .padding(Theme.dimens.padding)
                .height(345.dp)
        ) {
            AsyncImage(
                model = viewModel.currentPhoto,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
