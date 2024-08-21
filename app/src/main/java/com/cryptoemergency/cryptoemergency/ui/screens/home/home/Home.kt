package com.cryptoemergency.cryptoemergency.ui.screens.home.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.ScrollableScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    ScrollableScreen(
        padding = PaddingValues(0.dp),
    ) {
        ExchangeRate(viewModel)
        Spacer(Modifier.height(15.dp))
        Swiper(items)
        Spacer(Modifier.height(30.dp))
        Box(
            Modifier.padding(horizontal = Theme.values.padding)
        ) {
            Menu()
        }
    }
}

@Composable
private fun ExchangeRate(viewModel: HomeViewModel) {
    Box(
        modifier = Modifier.height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = viewModel.exchangeRate,
            style = Theme.typography.caption1,
            color = Theme.colors.text4,
            modifier = Modifier
                .basicMarquee()
        )
    }
}

@Composable
fun Swiper(items: List<String>) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val itemCount = items.size

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    if (dragAmount > 0 && currentIndex > 0) {
                        currentIndex--
                    } else if (dragAmount < 0 && currentIndex < itemCount - 1) {
                        currentIndex++
                    }
                }
            }
    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(300.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                ) {
                    Text(text = items[index], color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Composable
private fun Menu() {
    LazyVerticalGrid(
        // Так как не знаю конкретную высоту и нельзя указывать fillMaxHeight(ошибка бесконечнх высот),
        // задал maxHeight под предлогом: занимай сколько надо, но не выдавай ошибку
        modifier = Modifier.heightIn(max = 1500.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(MenuItems.entries.size) { index ->
            val item = MenuItems.entries[index]
            MenuItem(item.title, item.route, item.icon)
        }
    }
}

private enum class MenuItems(
    val title: String,
    val route: Routes,
    @DrawableRes val icon: Int,
) {
    NewsFeed("Лента", Routes.Page.NewsFeed, R.drawable.newsfeed),
    News("Новости", Routes.Page.News, R.drawable.news),
    Exchangers("Обменники", Routes.Page.Exchangers, R.drawable.exchangers),
    Exchanges("Биржи", Routes.Page.Exchanges, R.drawable.exchanges),
    Chat("Чат", Routes.Home.Chat, R.drawable.chat),
    Users("Пользователи", Routes.Page.Users, R.drawable.users),
    ICORating("ICO рейтинг", Routes.Page.ICORating, R.drawable.ico_rating),
    Startups("Стартапы", Routes.Page.Startups, R.drawable.startups),
    Web3("Работа Web3", Routes.Page.Web3, R.drawable.web3),
    Career("Карьеры", Routes.Page.Career, R.drawable.career),
    Academy("Академия", Routes.Page.Academy, R.drawable.academy),
    Wallet("CEM кошелек", Routes.Page.Wallet, R.drawable.wallet),
}

@Composable
private fun MenuItem(
    title: String,
    route: Routes,
    @DrawableRes icon: Int,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val navController = LocalNavController.current

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
            ) {
                navController.navigate(route)
            }
    ) {
        Row(
            modifier = Modifier
                .height(85.dp)
                .background(
                    color = Theme.colors.surface1,
                    shape = RoundedCornerShape(10.dp),
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Text(
                    text = title,
                    style = Theme.typography.h3,
                    color = Theme.colors.text6,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.align(Alignment.Top)
            ) {
                Box {
                    Icon(
                        painter = painterResource(id = icon),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .zIndex(2f)
                            .size(24.dp)
                            .align(Alignment.Center)
                    )

                    Icon(
                        painter = painterResource(Theme.icons.hexagonOnMainMenu),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.zIndex(1f)
                    )
                }
            }
        }
    }
}
