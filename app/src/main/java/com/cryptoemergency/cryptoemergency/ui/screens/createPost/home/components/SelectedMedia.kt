package com.cryptoemergency.cryptoemergency.ui.screens.createPost.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.CreatePostViewModel

fun LazyStaggeredGridScope.SelectedMedia(
    viewModel: CreatePostViewModel
) {
    item(span = StaggeredGridItemSpan.FullLine) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

        Box(
            modifier = Modifier.size(screenWidth)
        ) {
            if(viewModel.selectedMedia.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(Theme.icons.logo),
                        contentDescription = null,
                        modifier = Modifier.size(170.dp).alpha(.6f)
                    )
                }
            } else {
                HorizontalPager(
                    state = rememberPagerState(pageCount = { viewModel.selectedMedia.size }),
                ) { index ->
                    AsyncImage(
                        model = viewModel.selectedMedia[index],
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}
