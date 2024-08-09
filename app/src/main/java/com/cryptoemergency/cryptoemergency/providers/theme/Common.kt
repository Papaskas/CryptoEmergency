package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val accent: Color,
    val accentGradient: Color,

    // text
    // text

    val background: Color,
    val backgroundVariant: Color,
    val surface: Color,

    val success: Color,
    val error: Color,
)

data class Typography(
    val title: TextStyle,
    val titleBold: TextStyle,
    val subTitleBold: TextStyle,
    val subTitle: TextStyle,
    val text: TextStyle,
    val textBold: TextStyle,
    val textSecondary: TextStyle,
    val label: TextStyle,
    val subLabel: TextStyle,
    val fontText: TextStyle,
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
