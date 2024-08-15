package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

@Composable
fun NewsFeed() {
    Column {
        TitleSection(title = "Моя лента")
        Box(modifier = Modifier
            .weight(1f)
            .padding(Theme.shapes.padding)
        ) {
            Text(text = "Лента новостей пока пуста")
        }
    }
}