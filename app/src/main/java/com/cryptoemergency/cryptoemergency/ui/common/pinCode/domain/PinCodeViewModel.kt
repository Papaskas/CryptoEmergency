package com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

abstract class PinCodeViewModel : ViewModel() {
    val pinCode = mutableStateListOf<Int>()

    /**
     * Функция добавления значения в [pinCode] и вызова соответсвующих
     * */
    fun onClickIntButton(
        code: Int,
        maxPinLength: Int,
    ) {
        if (pinCode.size < maxPinLength) {
            pinCode.add(code)

            if (pinCode.size == maxPinLength) {
                onComplete()
            }
        }  else {
            onCrowded()
        }
    }

    fun onDeleteLastCode() {
        if (pinCode.isEmpty()) {
            onEmpty()
        } else {
            pinCode.removeLast()
        }
    }

    /**
     * Слушатель завершения ввода пин-кода
     * */
    abstract fun onComplete()

    /**
     * Слушатель переполнения пин-кода
     * */
    abstract fun onCrowded()

    /**
     * Слушатель отсутсвия первого элемента при его удалении в пин-коде
     * */
    abstract fun onEmpty()
}
