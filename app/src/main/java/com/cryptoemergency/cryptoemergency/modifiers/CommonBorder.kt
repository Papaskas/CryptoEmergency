package com.cryptoemergency.cryptoemergency.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.providers.theme.ThemeViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.currentTheme
import com.cryptoemergency.cryptoemergency.api.domain.model.store.data.CurrentTheme

@Composable
fun Modifier.commonBorder(
    isFocused: Boolean = false,
    isError: Boolean = false,
    shape: Shape = RoundedCornerShape(Theme.dimens.radius),
    vm: ThemeViewModel = hiltViewModel()
) = this.then(
    when {
        isError -> Modifier.border(1.dp, Theme.colors.error, shape)
        isFocused -> Modifier.border(1.dp, Theme.colors.accent, shape)
        currentTheme == CurrentTheme.DARK -> Modifier.border(1.dp, Theme.colors.stroke, shape)
        else -> Modifier
    }
)
