package com.cryptoemergency.cryptoemergency.ui.common.posts

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.modifiers.roundedHexagonShape
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.Media
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.Post
import com.cryptoemergency.cryptoemergency.types.PostViewType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PostItem(
    post: Post,
    viewType: PostViewType,
    modifier: Modifier = Modifier,
    mediaModifier: Modifier = Modifier,
) {
    val state = rememberPagerState { post.media.size }

    Column(modifier = modifier) {
        if (viewType == PostViewType.FULL) {
            HeaderNews(
                avatar = post.media[0].url,
                authorName = "Александр криптовалюта",
                createdAt = post.createdAt
            )
        }
        Content(
            media = post.media,
            state = state,
            mediaModifier = mediaModifier,
        )
        if (viewType == PostViewType.FULL) {
            Bottom(
                size = post.media.size,
                description = post.description,
                state = state,
            )
        }
    }
}

@Composable
private fun HeaderNews(
    avatar: String,
    authorName: String,
    createdAt: String,
) {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val date = LocalDate.parse(createdAt, format)
    val expanded = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = Theme.colors.surface2,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            )
            .padding(Theme.dimens.padding),
    ) {
        AsyncImage(
            model = avatar,
            contentDescription = authorName,
            modifier = Modifier
                .roundedHexagonShape()
                .size(36.dp),
            contentScale = ContentScale.Crop,
        )

        Spacer(Modifier.width(12.dp))

        Column {
            Text(
                text = authorName,
                color = Theme.colors.text1,
                style = Theme.typography.h4,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = date.toString(),
                color = Theme.colors.text2,
                style = Theme.typography.caption1,
            )
        }

        Spacer(Modifier.weight(1f))

        Box {
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    painter = painterResource(R.drawable.more),
                    contentDescription = null,
                )
            }

            DropMenu(expanded)
        }
    }
}

@Composable
private fun DropMenu(
    expanded: MutableState<Boolean>,
) {
    val context = LocalContext.current

    data class Type(
        val title: String,
        @DrawableRes val icon: Int,
    )

    val items = arrayOf(
        Type(context.getString(R.string.share), R.drawable.share__outline),
        Type(context.getString(R.string.copy_url), R.drawable.copy__outline),
        Type(context.getString(R.string.save), R.drawable.save__outline),
        Type(context.getString(R.string.unsubscribe), R.drawable.unsubscribe),
        Type(context.getString(R.string.report), R.drawable.report),
    )

    DropdownMenu(
        modifier = Modifier
            .background(color = Theme.colors.background2),
        expanded = expanded.value,
        onDismissRequest = {
            expanded.value = false
        },
    ) {
        items.forEach {
            DropdownMenuItem(
                onClick = {},
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = it.title,
                            tint = if (it.icon == R.drawable.report) Theme.colors.error else Theme.colors.text1
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = it.title,
                            style = Theme.typography.body1,
                            color = Theme.colors.text1
                        )
                    }
                }
            )
        }
    }

}

@Composable
private fun Content(
    media: List<Media>,
    state: PagerState,
    mediaModifier: Modifier,
) {
    Box(mediaModifier) {
        if (media.size > 1) {
            HorizontalPager(
                state = state,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                AsyncImage(
                    model = media[page].url,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        } else {
            AsyncImage(
                model = media[0].url,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        if (media.size > 1) {
            Row(
                Modifier
                    .offset(x = -(Theme.dimens.padding), y = Theme.dimens.padding)
                    .align(Alignment.TopEnd)
                    .background(
                        color = Theme.colors.background2.copy(alpha = .5f),
                        shape = CircleShape,
                    )
                    .padding(9.dp, 4.dp),
            ) {
                Text(
                    text = "${ state.currentPage + 1 }/${ media.size }",
                    style = Theme.typography.caption2,
                    color = Theme.colors.text1,
                )
            }
        }
    }
}

@Composable
private fun Bottom(
    size: Int,
    description: String?,
    state: PagerState,
) {
    Column(
        modifier = Modifier
            .background(
                color = Theme.colors.surface2,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
            )
            .padding(Theme.dimens.padding),
    ) {
        Pagination(size, state)
        Toolbar()
        Description(description)
    }
}

@Composable
private fun ColumnScope.Pagination(
    size: Int,
    state: PagerState,
) {
    if (size > 1) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(size) { index ->
                val boxSize by animateDpAsState(
                    targetValue = if (state.currentPage == index) 6.dp else 4.dp
                )
                val color by animateColorAsState(
                    targetValue = if (state.currentPage == index) Theme.colors.accent else Color.Gray
                )

                Box(
                    modifier = Modifier
                        .size(boxSize)
                        .background(
                            color = color,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
private fun Toolbar() {
    val context = LocalContext.current

    Row(
        Modifier.padding(bottom = Theme.dimens.padding)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.heart__outline),
                contentDescription = context.getString(R.string.like)
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.commnet),
                contentDescription = context.getString(
                    R.string.open,
                    context.resources.getQuantityString(R.plurals.comment, 99)
                )
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.share__outline),
                contentDescription = context.getString(R.string.share)
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.save__outline),
                contentDescription = context.getString(R.string.save)
            )
        }
    }
}

@Composable
private fun Description(
    description: String?,
) {
    val res = LocalContext.current.resources

    description?.let {
        Text(
            text = description,
            style = Theme.typography.body1,
            color = Theme.colors.text1,
        )
        Text(
            text = res.getString(
                R.string.show,
                res.getString(R.string.more).lowercase(),
            ),
            style = Theme.typography.body1,
            color = Theme.colors.accent,
        )
    }
}
