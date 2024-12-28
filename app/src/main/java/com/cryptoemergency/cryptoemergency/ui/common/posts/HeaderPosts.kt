package com.cryptoemergency.cryptoemergency.ui.common.posts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.papaska.core.entity.remote.post.PostViewEntity

@Composable
fun PostsHeader(
    postViewEntity: MutableState<PostViewEntity>,
    showFilterMenu: MutableState<Boolean>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.padding(horizontal = Theme.dimens.horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = context.getString(R.string.my_feed),
            style = Theme.typography.h2,
            color = Theme.colors.text1,
        )
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { showFilterMenu.value = true }) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "filter",
            )
        }
        IconButton(onClick = {
            postViewEntity.value = PostViewEntity.FULL
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_large),
                contentDescription = "sort by large",
                tint = if (postViewEntity.value == PostViewEntity.FULL) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
        IconButton(onClick = {
            postViewEntity.value = PostViewEntity.SHORT
        }) {
            Icon(
                painter = painterResource(R.drawable.sort_by_grid),
                contentDescription = "sort by grid",
                tint = if (postViewEntity.value == PostViewEntity.SHORT) {
                    Theme.colors.accent
                } else {
                    Theme.colors.text1
                },
            )
        }
    }
}
