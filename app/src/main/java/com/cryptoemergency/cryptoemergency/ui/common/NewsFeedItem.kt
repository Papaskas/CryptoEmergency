package com.cryptoemergency.cryptoemergency.ui.common

import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.model.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun NewsFeedItem(
    props: NewsFeedItemProps
) {
    val state = rememberPagerState { props.media.size }

    Column {
        TitleNews(
            props.avatar,
            props.authorName,
            props.createDate
        )
        Content(
            props.media,
            state,
        )
        Bottom(
            props.media,
            props.description,
            state,
        )
    }
}

@Composable
private fun TitleNews(
    avatar: Uri,
    authorName: String,
    createDate: String,
) {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(createDate, format)

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
                .clip(Theme.shapes.hexagonShape)
                .size(36.dp),
            contentScale = ContentScale.Crop
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

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.more),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun Content(
    media: List<Uri>,
    state: PagerState,
) {
    Box {
        HorizontalPager(state = state) { page ->
            AsyncImage(
                model = media[page],
                contentDescription = null,
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
                val color by animateColorAsState(targetValue = if (state.currentPage == index) Theme.colors.accent else Color.Gray)

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