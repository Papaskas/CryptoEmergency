package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme

@Composable
fun CommonSwitch(
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Switch(
        enabled = enabled,
        modifier = modifier,
        checked = state.value,
        onCheckedChange = { state.value = it },
        colors = SwitchDefaults.colors().copy(
            checkedThumbColor = Theme.colors.text6,
            checkedTrackColor = Theme.colors.accent,

            uncheckedThumbColor = Theme.colors.text6.copy(alpha = .5f),
            uncheckedTrackColor = Theme.colors.surface2,
            uncheckedBorderColor = Theme.colors.surface2,
        ),
    )
}

@Composable
fun CommonSwitch(
    state: MutableState<Boolean>,
    text: String,
    modifier: Modifier = Modifier,
    switchModifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = Theme.typography.body1,
            color = Theme.colors.text1,
        )
        Spacer(Modifier.weight(1f))
        CommonSwitch(
            enabled = enabled,
            modifier = switchModifier,
            state = state,
        )
    }
}
