package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val accent: Color,
    val gradient: Brush = Brush.linearGradient(
        colors = listOf(Color(0xFF0059F7), Color(0xFFF103CF))
    ),

    val buttonText: Color = Color(0xFFFFFFFF),

    val text1: Color,
    val text2: Color,
    val text3: Color,
    val text4: Color,
    val text5: Color,

    val bottomNav: Color,

    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,

    val surfaceQR: Color,
    val backgroundDropDownMenu: Color,
    val backgroundUserFeed: Color,

    val success: Color,
    val error: Color,
)

data class Typography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val body1: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
    val subscribersCount: TextStyle,
)

data class Shape(
    val padding: Dp,
)

object Theme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shaped: Shape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}

val LocalColors =
    staticCompositionLocalOf<Colors> {
        error("No colors provided")
    }

val LocalTypography =
    staticCompositionLocalOf<Typography> {
        error("No typography provided")
    }

val LocalShape =
    staticCompositionLocalOf<Shape> {
        error("No typography provided")
    }
