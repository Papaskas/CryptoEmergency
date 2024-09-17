package com.cryptoemergency.cryptoemergency.ui.common.bottomBar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.localNavController.getCurrentRoute
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        CommonHorizontalDivider()

        NavigationBar(
            containerColor = Theme.colors.background2,
        ) {
            val currentRoute = getCurrentRoute()

            getBottomItems().forEach { item ->
                val isSelected = currentRoute == item.route::class.qualifiedName

                if (item.label == null || item.icon == null) {
                    NavButton(isSelected)
                } else {
                    NavItem(
                        route = item.route,
                        icon = item.icon,
                        selected = isSelected,
                        label = item.label,
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.NavItem(
    label: String,
    @DrawableRes icon: Int,
    route: Routes,
    selected: Boolean,
) {
    val navController = LocalNavController.current

    NavigationBarItem(
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = label,
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
            }
        },
        label = {
            Text(
                text = label,
                style = Theme.typography.caption1,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Theme.colors.accent,
            selectedTextColor = Theme.colors.accent,
            unselectedIconColor = Theme.colors.text4,
            unselectedTextColor = Theme.colors.text4,
            indicatorColor = Color.Transparent,
        ),
    )
}

@Composable
private fun RowScope.NavButton(
    isSelected: Boolean,
) {
    val navController = LocalNavController.current

    NavigationBarItem(
        icon = {
            Box {
                Icon(
                    painter = painterResource(R.drawable.added_btn), // Окрашиваемая часть
                    contentDescription = "Добавить видео", // TODO: translate
                    tint = if (isSelected) Theme.colors.accent else Theme.colors.text4
                )
                Icon(
                    painter = painterResource(R.drawable.added_btn_plus), // Крестовина
                    contentDescription = null,
                    tint = Theme.colors.background2
                )
            }
        },
        selected = isSelected,
        onClick = {
            navController.navigate(Routes.Home.CreatePost) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
        ),
    )
}

@Composable
private fun getBottomItems (): List<BottomItem> {
    val res = LocalContext.current.resources

    return listOf(
        BottomItem(
            res.getString(R.string.home),
            R.drawable.home__filled,
            Routes.Home.Home,
        ),
        BottomItem(
            res.getString(R.string.news_feed,),
            R.drawable.news_feed__filled,
            Routes.Home.News,
        ),
        BottomItem(
            null,
            null,
            Routes.Home.CreatePost,
        ),
        BottomItem(
            res.getString(R.string.cems),
            R.drawable.cems,
            Routes.Home.Cems
        ),
        BottomItem(
            res.getString(R.string.more),
            R.drawable.more__filled,
            Routes.Home.Menu,
        )
    )
}

private data class BottomItem(
    val label: String?,
    @DrawableRes val icon: Int?,
    val route: Routes,
)
