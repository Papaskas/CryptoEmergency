package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

val shapes = CommonShape(
    hexagonShape = RoundedHexagonShape(16f),
)

private class RoundedHexagonShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val scale = size.width / 60f
            moveTo(43f * scale, 60.9f * scale)
            lineTo(22.8f * scale, 60.9f * scale)
            cubicTo(17.5f * scale, 61f * scale, 16.7f * scale, 60.6f * scale, 14f * scale, 56.3f * scale)
            lineTo(3.9f * scale, 38.1f * scale)
            cubicTo(1.6f * scale, 33f * scale, 1.5f * scale, 32.9f * scale, 4f * scale, 28.5f * scale)
            lineTo(14.8f * scale, 10f * scale)
            cubicTo(14.8f * scale, 10f * scale, 16.1f * scale, 7.6f * scale, 17.4f * scale, 6.9f * scale)
            cubicTo(18.8f * scale, 6.1f * scale, 22f * scale, 6.2f * scale, 22f * scale, 28.5f * scale)
            lineTo(43.1f * scale, 6.3f * scale)
            cubicTo(49.2f * scale, 6.2f * scale, 48.6f * scale, 6.9f * scale, 51.2f * scale, 10.6f * scale)
            lineTo(61.6f * scale, 28.6f * scale)
            cubicTo(63.5f * scale, 32.9f * scale, 64.1f * scale, 33.4f * scale, 61.6f * scale, 37.9f * scale)
            lineTo(51.9f * scale, 55.7f * scale)
            cubicTo(49.5f * scale, 61f * scale, 48.5f * scale, 60.9f * scale, 43f * scale, 60.9f * scale)
            close()
        }
        return Outline.Generic(path)
    }
}

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
