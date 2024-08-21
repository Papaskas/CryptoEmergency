package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

val shapes = CommonShape(
    hexagonShape = HexagonShape(),
)

private class HexagonShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2, 0f)
            lineTo(size.width, size.height / 4)
            lineTo(size.width, 3 * size.height / 4)
            lineTo(size.width / 2, size.height)
            lineTo(0f, 3 * size.height / 4)
            lineTo(0f, size.height / 4)
            close()
        }
        return Outline.Generic(path)
    }
}
