package com.cryptoemergency.cryptoemergency.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme

/**
 * Комопнент stories (cems)
 **/
@Composable
fun Stories() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            Story(
                R.drawable.added,
                "Добавить", // TODO: translate
            )
        }
        items(9) { index ->
            Story(
                R.drawable.avatar_placeholder,
                "Актуальное", // TODO: translate
            )
        }
    }
}

@Composable
private fun Story(
    @DrawableRes icon: Int,
    title: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier
                .height(60.dp)
                .width(55.dp)
                .background(
                    color = Theme.colors.surface1,
                    shape = Theme.shapes.hexagonShape,
                )
        )
        Spacer(Modifier.height(5.dp))
        Text(
            text = title,
            style = Theme.typography.caption1,
            color = Theme.colors.text2,
        )
    }
}
