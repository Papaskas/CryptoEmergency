package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Компонент горизонтальной пагинации в виде точек. Не работает если countDot <= 1
 *
 * @param countDot Количество точек пагинации
 * @param current Текущый выбраный индекс. Должен быть < [countDot]
 * @param spacedBy Расстояние между точками
 * @param selectDotSize Размер выбранной точки
 * @param unselectDotSize Размер не выбранных точек
 * @param dotShape Shape точек
 * @param selectedColor Цвет выбранной точки
 * @param unselectedColor Цвет остальных точек
 **/
@Composable
fun DottedPagination(
    countDot: Int,
    current: Int,
    alignment: Alignment.Horizontal,
    modifier: Modifier = Modifier,
    spacedBy: Dp = 8.dp,
    selectDotSize: Dp = 6.dp,
    unselectDotSize: Dp = 4.dp,
    dotShape: Shape = CircleShape,
    selectedColor: Color = Theme.colors.accent,
    unselectedColor: Color = Theme.colors.text3,
) {
    if (countDot <= 1) return

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(selectDotSize), // Предотвращение скачков при анимации
        horizontalArrangement = Arrangement.spacedBy(spacedBy, alignment),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Pagination(
            countDot,
            current,
            selectDotSize,
            unselectDotSize,
            dotShape,
            selectedColor,
            unselectedColor,
        )
    }
}

/**
 * Компонент вертикальной пагинации в виде точек. Не работает если countDot <= 1
 *
 * @param countDot Количество точек пагинации
 * @param current Текущый выбраный индекс. Должен быть < [countDot]
 * @param spacedBy Расстояние между точками
 * @param selectDotSize Размер выбранной точки
 * @param unselectDotSize Размер не выбранных точек
 * @param dotShape Shape точек
 * @param selectedColor Цвет выбранной точки
 * @param unselectedColor Цвет остальных точек
 **/
@Composable
fun DottedPagination(
    countDot: Int,
    current: Int,
    alignment: Alignment.Vertical,
    modifier: Modifier = Modifier,
    spacedBy: Dp = 8.dp,
    selectDotSize: Dp = 6.dp,
    unselectDotSize: Dp = 4.dp,
    dotShape: Shape = CircleShape,
    selectedColor: Color = Theme.colors.accent,
    unselectedColor: Color = Theme.colors.text3,
) {
    if (countDot <= 1) return

    Column (
        modifier = modifier
            .fillMaxHeight()
            .width(selectDotSize), // Предотвращение скачков при анимации
        verticalArrangement = Arrangement.spacedBy(spacedBy, alignment),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Pagination(
            countDot,
            current,
            selectDotSize,
            unselectDotSize,
            dotShape,
            selectedColor,
            unselectedColor,
        )
    }
}

@Composable
private fun Pagination(
    countDot: Int,
    current: Int,
    selectDotSize: Dp = 6.dp,
    unselectDotSize: Dp = 4.dp,
    dotShape: Shape = CircleShape,
    selectedColor: Color = Theme.colors.accent,
    unselectedColor: Color = Theme.colors.text3,
) {
    for (i in 0 until countDot) {
        val color by animateColorAsState(
            targetValue = if (i == current) selectedColor else unselectedColor
        )
        val size by animateDpAsState(
            targetValue = if (i == current) selectDotSize else unselectDotSize
        )

        Box(
            modifier = Modifier
                .size(size)
                .background(
                    color = color,
                    shape = dotShape,
                )
        )
    }
}
