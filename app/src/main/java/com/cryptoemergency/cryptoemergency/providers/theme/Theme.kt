package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalColors
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalDimens
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalIcons
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalShape
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTypography
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.DimensEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.IconsEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ShapeEntity
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.TypographyEntity
import com.papaska.core.entity.local.ThemeEntity

object Theme {
    val theme: MutableState<ThemeEntity>
        @Composable
        @ReadOnlyComposable
        get() = LocalTheme.current

    val colors: ColorsEntity
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: TypographyEntity
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: ShapeEntity
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current

    val icons: IconsEntity
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current

    val dimens: DimensEntity
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}

