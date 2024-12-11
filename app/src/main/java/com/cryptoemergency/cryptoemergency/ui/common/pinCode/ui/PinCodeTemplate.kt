package com.cryptoemergency.cryptoemergency.ui.common.pinCode.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain.PinCodeViewModel

/**
 * UI ввода пин-кодсм
 *
 * */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PinCodeTemplate(
    viewModel: PinCodeViewModel,
    modifier: Modifier = Modifier,
    maxItemsInEachRow: Int = 3,
    pinCodeLength: Int = 4,
    button: @Composable (
        number: Int,
        vm: PinCodeViewModel,
    ) -> Unit = { number, vm ->
        IntButton(number, vm, pinCodeLength)
    },
    leftSpecialButton: @Composable () -> Unit = { EmptyButton()  },
    rightSpecialButton: @Composable () -> Unit = { EmptyButton() },
    items: List<Int> = listOf(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9,
           0
    ),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
) {
    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = maxItemsInEachRow,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
    ) {
        repeat(9) {
            button(items[it], viewModel)
        }

        leftSpecialButton()
        button(items.last(), viewModel)
        rightSpecialButton()
    }
}

@Composable
private fun IntButton(
    number: Int,
    vm: PinCodeViewModel,
    pinCodeLength: Int,
) {
    IconButton(
        onClick = { vm.onClickIntButton(number, pinCodeLength) }
    ) {
        Text(text = number.toString())
    }
}

@Composable
private fun EmptyButton() {
    IconButton(
        enabled = false,
        onClick = {},
    ) {}
}
