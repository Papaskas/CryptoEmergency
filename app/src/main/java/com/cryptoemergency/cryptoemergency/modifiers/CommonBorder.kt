package com.cryptoemergency.cryptoemergency.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.papaska.domain.entity.local.ThemeEntity

@Composable
fun Modifier.commonBorder(
    isFocused: Boolean = false,
    isError: Boolean = false,
    shape: Shape = RoundedCornerShape(Theme.dimens.radius),
) = this.then(
    when {
        isError -> Modifier.border(1.dp, Theme.colors.error, shape)
        isFocused -> Modifier.border(1.dp, Theme.colors.accent, shape)
        LocalTheme.current.value == ThemeEntity.DARK -> Modifier.border(1.dp, Theme.colors.stroke, shape)
        else -> Modifier
    }
)
