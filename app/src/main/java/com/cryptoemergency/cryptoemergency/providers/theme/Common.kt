package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val accent: Color,
    val gradient: Brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0059F7),
            Color(0xFFF103CF)
        ),
        start = Offset(0f, 0f),
        end = Offset(500f, 300f) // Угол 45 градусов
    ),

    val buttonText: Color = Color(0xFFFFFFFF),

    val text1: Color,
    val text2: Color,
    val text3: Color,
    val text4: Color,
    val text5: Color,
    val text6: Color,

    val stroke: Color,

    val backgroundMain: Color,
    val background2: Color,
    val background3: Color,
    val surface1: Color,
    val surface2: Color,
    val surface3: Color,

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

data class CommonShape(
    val common: Dp,
    val hexagonShape: Shape,
    val diamondShape: Shape,
    val ticketShape: Shape,
    val starShape: Shape,
)

data class Values(
    val padding: Dp,
)

data class Icons(
    @DrawableRes val hexagonOnMainMenu: Int,
    @DrawableRes val logo: Int,
    @DrawableRes val theme: Int,
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

    val shapes: CommonShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current

    val icons: Icons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current

    val values: Values
        @Composable
        @ReadOnlyComposable
        get() = LocalValues.current
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
    staticCompositionLocalOf<CommonShape> {
        error("No shapes provided")
    }

val LocalIcons =
    staticCompositionLocalOf<Icons> {
        error("No icons provided")
    }

val LocalValues =
    staticCompositionLocalOf<Values> {
        error("No icons provided")
    }

