package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonTabs
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.newsFeed.NewsFeed

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val locale = LocalLocale.current

    Screen(
        padding = PaddingValues(0.dp),
    ) {
        Spacer(Modifier.height(35.dp))

        Box(
            modifier = Modifier
                .background(
                    color = Theme.colors.surface1,
                    shape = RoundedCornerShape(10.dp),
                )
                .fillMaxSize()
                .padding(top = Theme.values.padding)
        ) {
            CommonTabs(
                tabTitles = arrayOf(
                    locale.tabs.generalFeed,
                    locale.tabs.subscriptionFeed,
                    locale.tabs.allCems,
                    locale.tabs.cemsSubscriptions,
                    locale.tabs.subscriptionFeed,
                    locale.tabs.socialNetworks,
                    locale.tabs.bookmarks,
                ),
            ) { _ ->
                NewsFeed(
                    items = viewModel.items
                )
            }
        }
    }
}
