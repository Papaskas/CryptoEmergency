package com.cryptoemergency.cryptoemergency.providers.localNavController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.ui.common.bottomBar.BottomBar

object DestinationUtils {
    /**
     * Получение текущего [Destination], при его изменение вызовет перекомпозицию значения
     *
     * @return Возвращает класс типа: package com.cryptoemergency.cryptoemergency.navigation.Routes.Home.Home
     *
     * @sample Sample
     * @sample BottomBar
     * */
    @Composable
    fun getCurrentDestination(): String? {
        val navController = LocalNavController.current
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }
}

@Composable
private fun Sample() {
    val currentRoute = DestinationUtils.getCurrentDestination()

    data class SampleItem(
        val label: String,
        val route: Destination,
    )

    val items = listOf(
        SampleItem("Home", Destination.Home.Home),
        SampleItem("News Feed", Destination.Home.News),
        SampleItem("More", Destination.Home.Menu)
    )

    items.forEach { item ->
        val isSelected = currentRoute == item.route::class.qualifiedName

        Button(
            { },
            enabled = isSelected
        ) { Text(item.label) }
    }
}
