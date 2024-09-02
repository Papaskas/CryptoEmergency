package com.cryptoemergency.cryptoemergency.ui.common.newsFeed

import android.net.Uri
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.modifiers.roundedHexagonShape
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.types.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.types.NewsItemType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun NewsFeedItem(
    props: NewsFeedItemProps
) {
    val state = rememberPagerState { props.media.size }

    Column {
        if (props.type == NewsItemType.FULL) {
            HeaderNews(
                props.avatar,
                props.authorName,
                props.createDate
            )
        }
        Content(
            props.type,
            props.media,
            state,
        )
        if (props.type == NewsItemType.FULL) {
            Bottom(
                props.media,
                props.description,
                state,
            )
        }
    }
}

@Composable
private fun HeaderNews(
    avatar: Uri,
    authorName: String,
    createDate: String,
) {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(createDate, format)
    val expanded = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = Theme.colors.surface2,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            )
            .padding(Theme.values.padding),
    ) {
        AsyncImage(
            model = avatar,
            contentDescription = "Аватар $authorName",
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
    data class Type(
        val title: String,
        @DrawableRes val icon: Int,
    )
    val items = arrayOf(
        Type("Поделиться", R.drawable.share__outline),
        Type("Скопировать ссылку", R.drawable.copy__outline),
        Type("Сохранить", R.drawable.save__outline),
        Type("Пожаловаться", R.drawable.report),
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
    type: NewsItemType,
    media: List<Uri>,
    state: PagerState,
) {
    Box {
        if (media.size > 1) {
            HorizontalPager(state = state) { page ->
                AsyncImage(
                    model = media[page],
                    contentDescription = null,
                    modifier = Modifier
                        .then(
                            if (type == NewsItemType.SHORT) {
                                Modifier.height(124.dp)
                            } else {
                                Modifier.height(375.dp)
                            }
                        )
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        } else {
            AsyncImage(
                model = media[0],
                contentDescription = null,
                modifier = Modifier
                    .then(
                        if (type == NewsItemType.SHORT) {
                            Modifier.height(124.dp)
                        } else {
                            Modifier.height(375.dp)
                        }
                    )
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        if (media.size > 1) {
            Row(
                Modifier
                    .offset(x = -(Theme.values.padding), y = Theme.values.padding)
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
    media: List<Uri>,
    description: String?,
    state: PagerState,
) {
    Column(
        modifier = Modifier
            .background(
                color = Theme.colors.surface2,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
            )
            .padding(Theme.values.padding),
    ) {
        Pagination(media, state)
        Toolbar()
        Description(description)
    }
}

@Composable
private fun ColumnScope.Pagination(
    media: List<Uri>,
    state: PagerState,
) {
    if (media.size > 1) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(media.size) { index ->
                val size by animateDpAsState(targetValue = if (state.currentPage == index) 6.dp else 4.dp)
                val color by animateColorAsState(
                    targetValue = if (state.currentPage == index) Theme.colors.accent else Color.Gray
                )

                Box(
                    modifier = Modifier
                        .size(size)
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
    Row(
        Modifier.padding(bottom = Theme.values.padding)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.heart__outline),
                contentDescription = "Поставить лайк"
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.commnet),
                contentDescription = "Открыть комментарии"
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.share__outline),
                contentDescription = "Поделиться"
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.save__outline),
                contentDescription = "Поделиться"
            )
        }
    }
}

@Composable
private fun Description(
    description: String?,
) {
    description?.let {
        Text(
            text = description,
            style = Theme.typography.body1,
            color = Theme.colors.text1,
        )
        Text(
            text = "Показать еще",
            style = Theme.typography.body1,
            color = Theme.colors.accent,
        )
    }
}
