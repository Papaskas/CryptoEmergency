package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @param categories Массив заголовков для переключения между pages
 *
 * @param pages Массив переключаемых страниц в зависимости от текущей категории
 * */
@Composable
fun SliderCategories(
    categories: Array<String>,
    pages: Array<@Composable () -> Unit>,
) {
    var activeCategory by remember { mutableIntStateOf(0) }

    Column {
        LazyRow(
            modifier = Modifier.padding(bottom = 10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(categories) { index, title ->
                CategoryItem(
                    title = title,
                    isActive = title == categories[activeCategory],
                    onClick = {
                        activeCategory = index
                    },
                )
            }
        }

        Box {
            pages[activeCategory]()
        }
    }
}

@Composable
private fun CategoryItem(
    title: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) Color(0xFF333333) else Color(0xFF202020),
            contentColor = if (isActive) Color(0xFFE8E8E8) else Color(0xFFB8B8B8)
        ),
    ) {
        Text(text = title)
    }
}
