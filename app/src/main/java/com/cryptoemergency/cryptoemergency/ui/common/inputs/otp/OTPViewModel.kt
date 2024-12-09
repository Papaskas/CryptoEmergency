package com.cryptoemergency.cryptoemergency.ui.common.inputs.otp

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(

) : ViewModel() {
    lateinit var focusRequesters: List<FocusRequester>
    lateinit var focusManager: FocusManager
    lateinit var otpValues: List<MutableState<TextFieldValue>>
    private var keyboardController: SoftwareKeyboardController? = null
    lateinit var onUpdateValuesByIndex: (Int, TextFieldValue) -> Unit
    lateinit var onComplete: () -> Unit

    fun init(
        focusRequesters: List<FocusRequester>,
        focusManager: FocusManager,
        keyboardController: SoftwareKeyboardController?,
        otpValues: List<MutableState<TextFieldValue>>,
        onUpdateValuesByIndex: (Int, TextFieldValue) -> Unit,
        onComplete: () -> Unit,
    ) {
        this.focusRequesters = focusRequesters
        this.focusManager = focusManager
        this.keyboardController = keyboardController
        this.otpValues = otpValues
        this.onUpdateValuesByIndex = onUpdateValuesByIndex
        this.onComplete = onComplete
    }

    /**
     * Обрабатывает изменения значения поля ввода.
     *
     * @param newValue Новое значение, полученное из текстового поля.
     * @param otpLength Длина OTP, которую необходимо обработать.
     * @param index Индекс текущего поля ввода, изменившего значение.
     */
    fun onValueChange(
        newValue: TextFieldValue,
        otpLength: Int,
        index: Int,
    ) {
        when {
            newValue.text.length == otpLength -> handleLastSymbolOTP(newValue, otpLength)
            newValue.text.length <= 1 -> handleValueOTP(newValue, index, otpLength)
            else -> switchFocus(index, otpLength)
        }
    }

    /**
     * Обрабатывает последнее значение OTP.
     *
     * Эта функция обновляет все значения и завершает ввод,
     * если длина нового значения равна ожидаемой длине OTP.
     *
     * @param newValue Новое значение текста, содержащего полный OTP.
     */
    private fun handleLastSymbolOTP(newValue: TextFieldValue, otpLength: Int) {
        for (i in 0 until otpLength) {
            onUpdateValuesByIndex(i, newValue)
        }

        keyboardController?.hide()
        onComplete()
    }

    /**
     * Обрабатывает короткое значение поля ввода.
     *
     * Если новое значение имеет длину 0 или 1, обновляет соответствующее значение
     * и управляет переходом фокуса к следующему полю ввода.
     *
     * @param newValue Новое значение текста, полученное из поля ввода.
     * @param index Индекс текущего поля ввода.
     * @param otpLength Длина OTP, которая обрабатывается.
     */
    private fun handleValueOTP(newValue: TextFieldValue, index: Int, otpLength: Int) {
        onUpdateValuesByIndex(index, newValue)

        if (newValue.text.isNotEmpty()) {
            switchFocus(index, otpLength)
        }
    }

    /**
     * Меняет фокус на следующее поле ввода или завершает ввод.
     *
     * Если текущий индекс не последний, переключает фокус на следующее.
     * В противном случае, скрывает клавиатуру и завершает ввод OTP.
     *
     * @param index Индекс текущего поля ввода.
     * @param otpLength Длина OTP, которую необходимо обработать.
     */
    private fun switchFocus(index: Int, otpLength: Int) {
        if (index < otpLength - 1) {
            focusRequesters[index + 1].requestFocus()
        } else {
            keyboardController?.hide()
            focusManager.clearFocus()
            onComplete()
        }
    }

    fun onKeyEvent(
        keyEvent: KeyEvent,
        index: Int,
    ): Boolean {
        if (keyEvent.key == Key.Backspace) {
            if (otpValues[index].value.text.isEmpty() && index > 0) {
                onUpdateValuesByIndex(index, TextFieldValue())
                focusRequesters[index - 1].requestFocus()
            } else {
                onUpdateValuesByIndex(index, TextFieldValue())
            }

            return true
        } else {
            return false
        }
    }

    fun keyboardActions(index: Int, otpLength: Int) = KeyboardActions(
        onNext = {
            if (index < otpLength - 1) {
                focusRequesters[index + 1].requestFocus()
            }
        },
        onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
            onComplete()
        }
    )
}
