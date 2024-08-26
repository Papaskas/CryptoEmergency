package com.cryptoemergency.cryptoemergency.ui.common

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.model.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun NewsFeedItem(props: NewsFeedItemProps) {
    Column {
        TitleNews(
            props.avatar,
            props.authorName,
            props.createDate
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
                shape = RoundedCornerShape(10.dp),
            )
            .padding(Theme.values.padding),
    ) {
        AsyncImage(
            model = avatar,
            contentDescription = "Аватар $authorName",
            modifier = Modifier.clip(Theme.shapes.hexagonShape),
        )

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
