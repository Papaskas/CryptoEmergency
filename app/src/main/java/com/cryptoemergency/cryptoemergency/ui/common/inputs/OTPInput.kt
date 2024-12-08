package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 *
 * @throws IllegalArgumentException [value] > [otpCount]
 * */
@Composable
fun OTPInput(
    value: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    otpCount: Int = 6,
    onOtpTextChange: (TextFieldValue, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (value.value.text.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    Row {
        repeat(otpCount) {
            Input(
                value = value,
                label = "",
                onValueChange = {
                    if (it.text.length > otpCount) return@Input

                    onOtpTextChange(it, it.text.length == otpCount)
                }
            )
        }
    }

    Input(
        value = value,
        label = "ASD",
        onValueChange = {
            if (it.text.length > otpCount) return@Input

            onOtpTextChange(it, it.text.length == otpCount)
        }
    )

    BasicTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange(
                    it,
                    it.text.length == otpCount,
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = value.value.text
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        //index == text.length -> "0"
        index >= text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .border(
                1.dp, when {
                    isFocused -> Color(0xFF60626C)
                    else -> Color(0xFFB5B6BA)
                }, RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        text = char,
        color = if (isFocused) {
            Color(0xFFB5B6BA)
        } else {
            Color(0xFF60626C)
        },
        textAlign = TextAlign.Center
    )
}