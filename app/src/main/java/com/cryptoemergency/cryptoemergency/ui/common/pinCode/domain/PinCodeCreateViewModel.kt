package com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.cryptoemergency.cryptoemergency.lib.vibrate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *
 * Логика СОЗДАНИЯ пин кода
 *
 * */
@HiltViewModel
class PinCodeCreateViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
): PinCodeViewModel() {
    private val _message = mutableStateOf<String?>(null)
    val message: State<String?> = _message

    private var firstPinCode = ""

    /**
     * Слушатель завершения ввода пин-кода
     * */
    override fun onComplete() {
        when {
            firstPinCode.isEmpty() -> setFirstPinCode()
            firstPinCode.isNotEmpty() -> compareCodes()
        }
    }

    private fun setFirstPinCode() {
        _message.value = "Введите второй пин-код"
        firstPinCode = pinCode.joinToString("")
        pinCode.clear()
    }

    private fun compareCodes() {
        when {
            firstPinCode == pinCode.joinToString("") -> {
                _message.value = "Success!"
            }
            else -> {
                firstPinCode = ""
                pinCode.clear()
                _message.value = "Разные пароли!"
            }
        }
    }

    /**
     * Слушатель переполнения пин-кода
     * */
    override fun onCrowded() {
        _message.value = "Crowded pin-code"
        vibrate(context)
    }

    /**
     * Слушатель отсутсвия первого элемента при его удалении в пин-коде
     * */
    override fun onEmpty() {
        _message.value = "Empty pin-code"
        vibrate(context)
    }
}
