package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun MediaPager(
    media: List<Uri>
) {
    val size = LocalConfiguration.current.screenWidthDp.dp

    HorizontalPager(
        state = rememberPagerState { media.size },
        modifier = Modifier
            .padding(vertical = Theme.dimens.padding)
            .size(size)
            .clip(RoundedCornerShape(Theme.dimens.radius))
    ) {
        AsyncImage(
            model = media[it],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
