package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

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