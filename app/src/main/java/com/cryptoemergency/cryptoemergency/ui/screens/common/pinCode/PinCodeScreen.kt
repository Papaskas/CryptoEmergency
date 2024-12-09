package com.cryptoemergency.cryptoemergency.ui.screens.common.pinCode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cryptoemergency.cryptoemergency.ui.common.Screen

/**
 * Страница ввода пин кода
 *
 * @param vm [PinCodeViewModel] Бизнес логика пин кода. Передать [PinCodeCreateViewModel] или [PinCodeEnterViewModel]
 *
 * */
@Composable
fun PinCodeScreen(
    vm: PinCodeViewModel
) {
    Screen(
        topBar = {},
        bottomBar = {},
    ) {
        Column(Modifier.padding(it)) {
            PinCodeTemplate(vm = vm)
        }
    }
}
