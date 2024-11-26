package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Обьект примеров текстовых полей. Используются в JavaDoc
 * */
private object InputSamples {

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
    private fun DoublePasswordSample() {
        val values = Pair(
            remember { mutableStateOf(TextFieldValue()) },
            remember { mutableStateOf(TextFieldValue()) },
        )

        val hasErrors = Pair(
            remember { mutableStateOf(false) },
            remember { mutableStateOf(false) },
        )

        DoublePasswordsInput(
            values = values,
            hasErrors = hasErrors,
        )
    }
}
