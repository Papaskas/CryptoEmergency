package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider

/**
 * Заголовок для секций контента - лента новостей, социальные сети
 * */
@Composable
fun TitleSection(
    title: String,
    count: Int? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.padding(Theme.dimens.padding)
    ) {
        Row {
            Text(
                text = title,
                style = Theme.typography.h2,
                color = Theme.colors.text1,
            )

            if (count != null) {
                Text(
                    text = "$count",
                )
            }
        }

        Spacer(Modifier.weight(1f))

        if (onClick!= null) {
            Button(onClick = { /*TODO*/ }) {
                Text("click")
            }
        }
    }
    CommonHorizontalDivider()
}
