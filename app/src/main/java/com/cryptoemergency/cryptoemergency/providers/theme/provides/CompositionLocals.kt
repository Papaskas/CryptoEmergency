package com.cryptoemergency.cryptoemergency.providers.theme.provides

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.DimensEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.IconsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ShapeEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.TypographyEntity
import com.papaska.core.entity.local.ThemeEntity

object CompositionLocals {
    val LocalTheme = compositionLocalOf<MutableState<ThemeEntity>> {
        error("No theme provided")
    }

    val LocalColors = compositionLocalOf<ColorsEntity> {
        error("No colors provided")
    }

    val LocalIcons = compositionLocalOf<IconsEntity> {
        error("No icons provided")
    }

    val LocalTypography = staticCompositionLocalOf<TypographyEntity> {
        error("No typography provided")
    }

    val LocalShape = staticCompositionLocalOf<ShapeEntity> {
        error("No shapes provided")
    }

    val LocalDimens = staticCompositionLocalOf<DimensEntity> {
        error("No dimens provided")
    }
}