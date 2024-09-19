package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.sections

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonTabs
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworks.AddSocialNetworks
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.newsFeed.NewsFeed

/**
 * Блок основного контента профиля: социальные сети, лента новостей и т.п.
 * */
@Composable
fun Content(
    viewModel: ProfileViewModel
) {
    Surface(
        color = Theme.colors.surface1,
        shape = RoundedCornerShape(
            topStart = Theme.dimens.shape,
            topEnd = Theme.dimens.shape,
        ),
        modifier = Modifier
            .fillMaxSize()
            .heightIn(min = 500.dp),
    ) {
        Tabs()
    }
}

@Composable
private fun Tabs() {
    val res = LocalContext.current.resources

    val titles = arrayOf(
        res.getString(R.string.general_feed),
        res.getString(R.string.subscription_feed),
        res.getString(R.string.all_cems),
        res.getString(R.string.cems_subscriptions),
        res.getString(R.string.subscription_feed),
        res.getQuantityString(R.plurals.social_network, 3),
        res.getString(R.string.bookmark),
    )
    val content = arrayOf<@Composable () -> Unit>(
        { NewsFeed() },
        { NewsFeed() },
        { NewsFeed() },
        { NewsFeed() },
        { NewsFeed() },
        { AddSocialNetworks() },
        { NewsFeed() },
    )

    CommonTabs(
        modifier = Modifier.fillMaxSize(),
        tabTitles = titles,
    ) { page ->
        content[page]()
    }
}
