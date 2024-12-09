package com.cryptoemergency.cryptoemergency.ui.screens.common.pinCode

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PinCodeTemplate(
    vm: PinCodeTemplateViewModel,
    modifier: Modifier = Modifier,
    maxItemsInEachRow: Int = 3,
    leftButton: @Composable () -> Unit = { Box { } },
    rightButton: @Composable () -> Unit = { Box { } },
    items: List<Any> = listOf(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9,
        leftButton, 0, rightButton
    ),
) {
    FlowRow(
        maxItemsInEachRow = maxItemsInEachRow,
        modifier = modifier,
    ) {
        items.forEach { button ->
            when (button) {
                is Int -> IntButton(button.toString(), vm)
                else -> {
                    val composableButton = button as @Composable () -> Unit
                    composableButton()
                }
            }
        }
    }
}

@Composable
private fun IntButton(
    text: String,
    vm: PinCodeTemplateViewModel,
) {
    IconButton(
        onClick = { vm.onClickIntButton() }
    ) {
        Text(
            text = text,
        )
    }
}
