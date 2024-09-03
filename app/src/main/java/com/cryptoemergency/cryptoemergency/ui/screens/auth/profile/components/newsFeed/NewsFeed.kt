package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.newsFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.TitleSection

/**
 * Блок в секции content - лента новостей
 * */
@Composable
fun NewsFeed() {
    val locale = LocalLocale.current

    Column {
        TitleSection(title = locale.newsFeedSection.myFeed)
        EmptyProfilePage(
            title = locale.newsFeedSection.emptyTitle,
            message = locale.newsFeedSection.emptyDescription,
            buttonText = locale.newsFeedSection.addNews,
            onClick = {}
        )
    }
}
