package com.cryptoemergency.cryptoemergency.providers.theme.provides.entity

import androidx.compose.ui.graphics.Color

data class ColorsEntity(
    val accent: Color,
    val logoPink: Color,
    val logoBlue: Color,

    val buttonText: Color = Color(0xFFFFFFFF),

    val text1: Color,
    val text2: Color,
    val text3: Color,
    val text4: Color,
    val text5: Color,
    val text6: Color,

    val stroke: Color,
    val strokeVariant: Color,

    val backgroundMain: Color,
    val background2: Color,
    val background3: Color,
    val background4: Color,

    val surface1: Color,
    val surface2: Color,
    val surface3: Color,

    val success: Color,
    val error: Color,
)