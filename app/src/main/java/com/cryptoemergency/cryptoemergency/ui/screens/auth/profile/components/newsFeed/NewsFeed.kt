package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.newsFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.TitleSection

/**
 * Блок в секции content - лента новостей
 * */
@Composable
fun NewsFeed() {
    val res = LocalContext.current.resources

    Column {
        TitleSection(title = res.getString(R.string.my_feed))
        EmptyProfilePage(
            title = res.getString(
                R.string.empty,
                res.getQuantityString(
                    R.plurals.news,
                    5,
                ),
            ),
            message = res.getString(
                R.string.empty,
                res.getQuantityString(
                    R.plurals.news,
                    5,
                ),
            ),
            buttonText = res.getString(
                R.string.add,
                res.getQuantityString(
                    R.plurals.news,
                    2,
                ).lowercase(),
            ),
            onClick = {}
        )
    }
}
