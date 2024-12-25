package com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes

import androidx.compose.ui.graphics.Color
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity

val lightPalette = ColorsEntity(
    accent = LightColors.accent,

    logoPink = LightColors.logoPink,
    logoBlue = LightColors.logoBlue,

    text1 = LightColors.B100,
    text2 = LightColors.G,
    text3 = LightColors.G,
    text4 = LightColors.G,
    text5 = LightColors.B90,
    text6 = LightColors.B100,

    stroke = LightColors.StrokeB20,
    strokeVariant = LightColors.StrokeB30,

    backgroundMain = LightColors.BgW100,
    background2 = LightColors.BgW90,
    background3 = LightColors.BgW90,
    background4 = LightColors.BgG,

    surface1 = LightColors.BgG,
    surface2 = LightColors.BgG,
    surface3 = LightColors.BgW90,

    success = LightColors.success,
    error = LightColors.error,
)

private object LightColors {
    val accent = Color(0xFF8E4BFF)

    val logoPink = Color(0xFFC126CE)
    val logoBlue = Color(0xFF284CCB)

    val W100 = Color(0xFFFFFFFF)
    val B100 = Color(0xFF343434)
    val B90 = Color(0xFF474C4F)
    val G = Color(0xFF999FAB)

    val BgW100 = Color(0xFFFFFFFF)
    val BgW90 = Color(0xFFFCFCFC)
    val BgG = Color(0xFFF4F4F4)

    val StrokeB20 = Color(0xFFEFEFEF)
    val StrokeB30 = Color(0xFFE7E8EA)

    val error = Color(0xFFF34942)
    val success = Color(0xFF0AB258)
}
