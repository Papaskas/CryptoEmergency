package com.cryptoemergency.cryptoemergency.ui.common.bottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.cryptoemergency.cryptoemergency.R

@Composable
fun BottomBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Home",
                )
            },
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Search",
                )
            },
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Create",
                )
            },
            selected = selectedIndex == 2,
            onClick = { selectedIndex = 2 },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Notifications",
                )
            },
            selected = selectedIndex == 3,
            onClick = { selectedIndex = 3 },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Profile",
                )
            },
            selected = selectedIndex == 4,
            onClick = { selectedIndex = 4 },
        )
    }
}
