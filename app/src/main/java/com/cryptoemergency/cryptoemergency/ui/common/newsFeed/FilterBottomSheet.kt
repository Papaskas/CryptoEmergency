package com.cryptoemergency.cryptoemergency.ui.common.newsFeed

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider

/**
 * @param selectedFilter Состояние выбранного пункта
 * @param showBottomSheet Состояние показа экрана
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    selectedFilter: MutableState<String>,
    showBottomSheet: MutableState<Boolean>,
) {
    val locale = LocalLocale.current

    val items = arrayOf(
        ItemType(R.drawable.infinity, locale.filter.all),
        ItemType(R.drawable.image, locale.filter.photo),
        ItemType(R.drawable.video, locale.filter.video),
        ItemType(R.drawable.text, locale.filter.text),
    )

    BottomSheet(
        showBottomSheet = showBottomSheet,
        title = locale.titles.filter,
        contentPadding = 0.dp,
    ) {
        items.forEach {
            Item(
                state = selectedFilter,
                props = it,
            )
        }
    }
}

@Composable
private fun Item(
    state: MutableState<String>,
    props: ItemType,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
            ) {
                state.value = props.title
            },
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .padding(horizontal = Theme.values.padding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(props.icon),
                contentDescription = props.title,
                tint = if (state.value == props.title) Theme.colors.accent else Theme.colors.text4,
                modifier = Modifier.size(24.dp)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = props.title,
                style = Theme.typography.body1,
                color = if (state.value == props.title) Theme.colors.accent else Theme.colors.text1,
            )
        }
    }

    CommonHorizontalDivider()
}

private data class ItemType(
    @DrawableRes val icon: Int,
    val title: String,
)
