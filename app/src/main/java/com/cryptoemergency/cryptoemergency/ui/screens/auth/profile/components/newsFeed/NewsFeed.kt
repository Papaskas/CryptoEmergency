package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.newsFeed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.TitleSection

@Composable
fun NewsFeed() {
    Column {
        TitleSection(title = "Моя лента")
        EmptyProfilePage(
            title = "Новостной список пуст",
            message = "Добавьте новости\nв свой профиль",
            buttonText = "Добавить новость",
            onClick = {

            }
        )
    }
}