package com.cryptoemergency.cryptoemergency.providers.theme.provides

import androidx.compose.runtime.staticCompositionLocalOf
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.DimensEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.IconsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ShapeEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.TypographyEntity
import com.papaska.core.entity.local.ThemeEntity

object CompositionLocals {
    val LocalTheme = staticCompositionLocalOf<ThemeEntity> {
        error("No theme provided")
    }

    val LocalColors = staticCompositionLocalOf<ColorsEntity> {
        error("No colors provided")
    }

    val LocalTypography = staticCompositionLocalOf<TypographyEntity> {
        error("No typography provided")
    }

    val LocalShape = staticCompositionLocalOf<ShapeEntity> {
        error("No shapes provided")
    }

    val LocalIcons = staticCompositionLocalOf<IconsEntity> {
        error("No icons provided")
    }

    val LocalDimens = staticCompositionLocalOf<DimensEntity> {
        error("No dimens provided")
    }
}