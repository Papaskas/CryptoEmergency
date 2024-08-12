package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.ui.graphics.Color

val darkPalette = Colors(
    accent = DarkColors.accent,

    text1 = DarkColors.TextW90,
    text2 = DarkColors.TextG40,
    text3 = DarkColors.TextG100,
    text4 = DarkColors.TextG20,
    text5 = DarkColors.TextG20,
    text6 = DarkColors.TextW100,

    bottomNav = DarkColors.BgB90,

    background = DarkColors.BgB100,
    surface = DarkColors.BgB80,
    surfaceVariant = DarkColors.BgG100,
    surfaceQR = DarkColors.BgB80,

    backgroundDropDownMenu = DarkColors.BgB90,

    backgroundUserFeed = DarkColors.BgG100,

    success = DarkColors.success,
    error = DarkColors.error,
)

val lightPalette = Colors(
    accent = LightColors.accent,

    text1 = LightColors.TextB100,
    text2 = LightColors.TextG,
    text3 = LightColors.TextG,
    text4 = LightColors.TextG,
    text5 = LightColors.TextB90,
    text6 = LightColors.TextB100,

    bottomNav = LightColors.BgW100,

    background = LightColors.BgW100,
    surface = LightColors.BgG,
    surfaceVariant = LightColors.BgG,
    surfaceQR = LightColors.BgW90,

    backgroundDropDownMenu = LightColors.BgW100,

    backgroundUserFeed = LightColors.BgW90,

    success = LightColors.success,
    error = LightColors.error,
)

private object DarkColors {
    val accent = Color(0xFFAB55FF)

    val TextW100 = Color(0xFFFFFFFF)
    val TextW90 = Color(0xFFF8F8FA)
    val TextG100 = Color(0xFF5B5D5D)
    val TextG20 = Color(0xFFC4C8C7)
    val TextG40 = Color(0xFF878A89)

    val BgB100 = Color(0xFF151516)
    val BgB90 = Color(0xFF161617)
    val BgB80 = Color(0xFF212121)
    val BgG100 = Color(0xFF2A2A2A)

    val StrokeB = Color(0xFF272728)

    val error = Color(0xFFFF5555)
    val success = Color(0xFF6ED960)
}

private object LightColors {
    val accent = Color(0xFF8E4BFF)

    val TextW100 = Color(0xFFFFFFFF)
    val TextB100 = Color(0xFF343434)
    val TextB90 = Color(0xFF474C4F)
    val TextG = Color(0xFF999FAB)

    val BgW100 = Color(0xFFFFFFFF)
    val BgW90 = Color(0xFFFCFCFC)
    val BgG = Color(0xFFF4F4F4)

    val StrokeB20 = Color(0xFFEFEFEF)
    val StrokeB30 = Color(0xFFE7E8EA)

    val error = Color(0xFFF34942)
    val success = Color(0xFF0AB258)
}
