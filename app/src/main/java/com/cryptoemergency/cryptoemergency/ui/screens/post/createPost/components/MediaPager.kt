package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.cryptoemergency.cryptoemergency.ui.common.DottedPagination
import com.cryptoemergency.cryptoemergency.ui.screens.post.CreatePostViewModel

@Composable
fun MediaPager(
    viewModel: CreatePostViewModel,
) {
    val size = if(viewModel.selectedMedia.size > 1) {
        LocalConfiguration.current.screenWidthDp.dp - Theme.dimens.horizontalPadding * 2 - 34.dp
    } else {
        LocalConfiguration.current.screenWidthDp.dp
    }

    val state = rememberPagerState { viewModel.selectedMedia.size }

    Column {
        HorizontalPager(
            state = state,
            beyondViewportPageCount = 1,
            pageSpacing = -(33.dp + Theme.dimens.horizontalPadding * 2),
            modifier = Modifier
                .then(
                    if(viewModel.selectedMedia.size > 1) {
                        Modifier.padding(vertical = Theme.dimens.horizontalPadding)
                    } else {
                        Modifier.padding(Theme.dimens.horizontalPadding)
                    }
                )
                .height(size)
                .clip(RoundedCornerShape(Theme.dimens.radius))
        ) {
            Box(
                Modifier.size(size)
            ) {
                AsyncImage(
                    model = viewModel.selectedMedia[it],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )

                DeleteMediaButton(
                    viewModel.selectedMedia,
                    viewModel.selectedMedia[it]
                )
            }
        }

        if (viewModel.selectedMedia.size > 1) {
            Spacer(Modifier.height(10.dp))

            DottedPagination(
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.CenterHorizontally,
                countDot = viewModel.selectedMedia.size,
                current = state.currentPage,
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun BoxScope.DeleteMediaButton(
    mediaList: SnapshotStateList<Uri>,
    currentMedia: Uri,
) {
    IconButton(
        onClick = {
            mediaList.remove(currentMedia)
        },
        modifier = Modifier
            .offset(
                y = 5.dp,
                x = -(5).dp,
            )
            .align(Alignment.TopEnd)
            .clip(CircleShape)
            .background(Theme.colors.error)
    ) {
        Icon(
            painter = painterResource(R.drawable.close),
            contentDescription = "Удалить медиа", // TODO: translate
            tint = Theme.colors.text6,
            modifier = Modifier.size(50.dp)
        )
    }
}
