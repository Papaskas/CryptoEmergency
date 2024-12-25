package com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes

import androidx.compose.ui.graphics.Color
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity

val darkPalette = ColorsEntity(
    accent = DarkColors.accent,
    logoPink = DarkColors.logoPink,
    logoBlue = DarkColors.logoBlue,

    text1 = DarkColors.W90,
    text2 = DarkColors.G40,
    text3 = DarkColors.G100,
    text4 = DarkColors.G20,
    text5 = DarkColors.G20,
    text6 = DarkColors.W100,

    stroke = DarkColors.StrokeB,
    strokeVariant = DarkColors.StrokeB,

    backgroundMain = DarkColors.BgB100,
    background2 = DarkColors.BgB90,
    background3 = DarkColors.BgG100,
    background4 = DarkColors.StrokeB,

    surface1 = DarkColors.BgB80,
    surface2 = DarkColors.BgG100,
    surface3 = DarkColors.BgB80,

    success = DarkColors.success,
    error = DarkColors.error,
)

private object DarkColors {
    val accent = Color(0xFFAB55FF)

    val logoPink = Color(0xFFC126CE)
    val logoBlue = Color(0xFF284CCB)

    val W100 = Color(0xFFFFFFFF)
    val W90 = Color(0xFFF8F8FA)
    val G100 = Color(0xFF5B5D5D)
    val G20 = Color(0xFFC4C8C7)
    val G40 = Color(0xFF878A89)

    val BgB100 = Color(0xFF151516)
    val BgB90 = Color(0xFF161617)
    val BgB80 = Color(0xFF212121)
    val BgG100 = Color(0xFF2A2A2A)

    val StrokeB = Color(0xFF272728)

    val error = Color(0xFFFF5555)
    val success = Color(0xFF6ED960)
}
