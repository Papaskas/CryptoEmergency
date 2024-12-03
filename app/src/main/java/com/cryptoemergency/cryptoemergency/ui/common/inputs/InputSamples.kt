package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.lib.validation.ValidatorPatterns
import com.cryptoemergency.cryptoemergency.ui.common.inputs.phoneInput.PhoneInput
import com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput.ValidatorInput

/**
 * Обьект примеров текстовых полей. Используются в JavaDoc
 * */
object InputSamples {

    @Composable
    private fun InputSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }

        Input(
            value = value,
            label = "Sample"
        )
    }

    @Composable
    private fun EmailInputSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }
        val hasError = remember { mutableStateOf(false) }

        EmailInput(
            value = value,
            hasError = hasError,
        )
    }

    @Composable
    private fun PasswordSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }
        val hasError = remember { mutableStateOf(false) }

        PasswordInput(
            value = value,
            hasError = hasError,
        )
    }

    @Composable
    private fun DoublePasswordSample() {
        val values = Pair(
            remember { mutableStateOf(TextFieldValue()) },
            remember { mutableStateOf(TextFieldValue()) },
        )

        DoublePasswordsInput(values = values)
    }

    @Composable
    private fun ValidatorInputSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }
        val validators = listOf(
            ValidatorPatterns.notEmpty, // Не пустое
            ValidatorPatterns.withoutSpaces, // Без пробелов
            ValidatorPatterns.onlyNumber, // Только цифры
            ValidatorPatterns.inRange(6, 12), // В размере от 6 до 12
        )

        ValidatorInput(
            value = value,
            label = "Sample",
            validators = validators,
        )
    }

    @Composable
    private fun MultilineSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }
        val hasError = remember { mutableStateOf(false) }

        MultiLineInput(
            value = value,
            hasError = hasError,
            minSymbols = 0,
            maxSymbols = 120,
            minLines = 1,
            maxLines = 2,
            label = "Sample",
        )
    }

    @Composable
    private fun MultilineWithoutValidSymbolSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }
        val hasError = remember { mutableStateOf(false) }

        MultiLineInput(
            value = value,
            hasError = hasError,
            minLines = 1,
            maxLines = 2,
            label = "Sample",
        )
    }

    @Composable
    private fun PhoneInputSample() {
        val value = remember { mutableStateOf(TextFieldValue()) }
        val hasError = remember { mutableStateOf(false) }

        PhoneInput(
            value = value,
            hasError = hasError,
        )
    }
}
