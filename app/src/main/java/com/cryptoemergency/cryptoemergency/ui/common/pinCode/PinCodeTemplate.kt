package com.cryptoemergency.cryptoemergency.ui.common.pinCode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.viewModels.PinCodeCreateViewModel
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.viewModels.PinCodeEnterViewModel
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.viewModels.PinCodeViewModel

/**
 * Панель пин-кода
 *
 * @param viewModel [PinCodeViewModel] Бизнес-логика панели пин-кода. Передать наследника [PinCodeViewModel].
 * [PinCodeEnterViewModel], если нужна логика ввода пин-кода. [PinCodeCreateViewModel],
 * если нужна логика создания пин-кода.
 * @param modifier [Modifier] Применяемый к разметке
 * @param maxItemsInEachRow [Int] количество цифр в строке
 * @param pinCodeLength [Int] Длина пин-кода
 * @param items [List] Порядок расстановки цифр. Последний размещается все слева от [leftSpecialButton]
 * и справа от [rightSpecialButton], даже если они не заданы
 * @param horizontalArrangement [Arrangement.Horizontal] The horizontal arrangement of the layout's children
 * @param verticalArrangement [Arrangement.Vertical] The vertical arrangement of the layout's virtual rows
 * @param button [Composable] Основная кнопка. Обязательно должна иметь вызов метода [viewModel.onClickIntButton]
 * @param leftSpecialButton [Composable] Левая кнопка специального действия. По умолчанию пустая
 * @param rightSpecialButton [Composable] Правая кнопка специального действия. По умолчанию пустая
 *
 * @sample SampleCreatePinCode
 * @sample SampleEnterPinCode
 *
 * */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PinCodeTemplate(
    viewModel: PinCodeViewModel,
    modifier: Modifier = Modifier,
    maxItemsInEachRow: Int = 3,
    pinCodeLength: Int = 4,
    items: List<Int> = listOf(
        1, 2, 3,
        4, 5, 6,
        7, 8, 9,
        0
    ),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    button: @Composable (
        number: Int,
        vm: PinCodeViewModel,
    ) -> Unit = { number, vm ->
        IntButton(number, vm, pinCodeLength)
    },
    leftSpecialButton: @Composable () -> Unit = { EmptyButton()  },
    rightSpecialButton: @Composable () -> Unit = { EmptyButton() },
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

@Composable
private fun SampleCreatePinCode() {
    val vm: PinCodeCreateViewModel = hiltViewModel()

    PinCodeTemplate(
        viewModel = vm,
        rightSpecialButton = {
            IconButton({ vm.onDeleteLastCode() }) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = null,
                )
            }
        }
    )
}

@Composable
private fun SampleEnterPinCode() {
    val vm: PinCodeEnterViewModel = hiltViewModel()

    PinCodeTemplate(
        viewModel = vm,
        rightSpecialButton = {
            IconButton({ vm.onDeleteLastCode() }) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = null,
                )
            }
        }
    )
}
