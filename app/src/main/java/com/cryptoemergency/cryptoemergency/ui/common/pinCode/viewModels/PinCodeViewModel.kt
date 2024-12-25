package com.cryptoemergency.cryptoemergency.ui.common.pinCode.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

abstract class PinCodeViewModel : ViewModel() {
    protected val pinCode = mutableStateListOf<Int>()

    private var onCompleteListener: (pinCode: String) -> Unit = {}
    private var onCrowdedListener: () -> Unit = {}
    private var onEmptyListener: () -> Unit = {}

    /**
     * Коллбэк завершения ввода пин-кода
     *
     * @return [String] Значение пинкода
     * */
    fun setOnCompleteListener(listener: (pinCode: String) -> Unit) {
        onCompleteListener = listener
    }

    /**
     * Коллбэк ошибки переполнения пин-кода
     * */
    fun setOnCrowdedListener(listener: () -> Unit) {
        onCrowdedListener = listener
    }

    /**
     * Коллбэк ошибки отсутсвия первого элемента при его удалении в пин-коде
     * */
    fun setOnEmptyListener(listener: () -> Unit) {
        onEmptyListener = listener
    }

    protected fun notifyCompleteListener() {
        onCompleteListener(pinCode.joinToString(""))
    }

    protected fun notifyCrowdedListener() {
        onCrowdedListener()
    }

    protected fun notifyEmptyListener() {
        onEmptyListener()
    }

    /**
     * Функция добавления значения в [pinCode]
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

    /**
     * Функция удаления последнего значения из [pinCode]
     * */
    fun onDeleteLastCode() {
        if (pinCode.isEmpty()) {
            onEmpty()
        } else {
            pinCode.removeLastOrNull()
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
