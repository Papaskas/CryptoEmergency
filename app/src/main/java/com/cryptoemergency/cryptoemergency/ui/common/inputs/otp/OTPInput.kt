package com.cryptoemergency.cryptoemergency.ui.common.inputs.otp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input

/**
 * [OTPInputTextFields] is a custom OTP input fields for the OTP verification.
 * In this composable function, [onComplete] callback is invoked when all OTP input fields are filled,
 * or when user presses the [ImeAction.Done] ime button. (If you are controlling it (i.e., invoking validations and navigation)
 * from other [Composable] like [Button] then you can simply pass empty lambda `{}` as callback).
 *
 *  See end of this file for `example of usage`.
 *
 * @param otpLength length of OTP code (this determines the number of [OutlinedTextField] for OTP code input)
 * @param otpValues list of [String] OTP values entered by the user. The length of this list is determined by otpLength.
 * Pass this `List(otpLength) { "" }` as default for future reference (To use, it should be passed from
 * the viewModel for value update).
 * @param onUpdateValuesByIndex callback is invoked when OTP value is updated by the user. For each respective
 * input field it returns `index` of the list item where it is updating, and `value` of the OTP for that specific index.
 * @param onComplete callback to be invoked when OTP input is complete. (This is where we validate the
 * OTP code, navigate to next screen, or call any function as per requirement) - pass all these as argument.
 * In the below function [OTPInputTextFields], it is invoked when all OTP input fields are filled (till last),
 * or when user presses the [ImeAction.Done] ime button.
 * @param hasError is for dynamic feedback to User for OTP input after validation. Changes the color of text fields as error red.
 * Ignore this if not needed (i.e., default to `false`).
 *
 * - Just for more understanding and reference, `List(otpLength) { "" }` creates a list of `otpLength` size i.e, if `otpLength` is 4, then it will create
 * list of size 4 with all elements as empty string, which we will update using [onUpdateValuesByIndex] callback.
 */
@Composable
fun OTPInputTextFields(
    otpLength: Int,
    otpValues: List<MutableState<TextFieldValue>>,
    onUpdateValuesByIndex: (Int, TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {},
    hasError: Boolean = false,
    vm: OTPViewModel = hiltViewModel(),
) {
    vm.init(
        focusRequesters = List(otpLength) { FocusRequester() },
        focusManager = LocalFocusManager.current,
        keyboardController = LocalSoftwareKeyboardController.current,
        otpValues = otpValues,
        onUpdateValuesByIndex = onUpdateValuesByIndex,
        onComplete = onComplete
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
//        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        vm.otpValues.forEachIndexed { index, value ->
            Input(
                modifier = Modifier
                    .weight(1f)
                    .height(400.dp)
                    .padding(6.dp)
                    .focusRequester(vm.focusRequesters[index])
                    .onKeyEvent {
                        vm.onKeyEvent(it, index)
                    },
                value = value,
                onValueChange = {
                    vm.onValueChange(it, otpLength, index)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (index == otpLength - 1) ImeAction.Done else ImeAction.Next
                ),
                keyboardActions = vm.keyboardActions(index, otpLength),
                shape = MaterialTheme.shapes.small,
                hasError = hasError,
                label = ""
            )

            LaunchedEffect(value) {
                if (otpValues.all { it.value.text.isNotEmpty() }) {
                    vm.focusManager.clearFocus()
                    vm.onComplete()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        vm.focusRequesters.first().requestFocus()
    }
}
