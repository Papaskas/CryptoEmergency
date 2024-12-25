package com.cryptoemergency.cryptoemergency.ui.common.pinCode.viewModels

import android.content.Context
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
    private var onFirstStepComplete: () -> Unit = {}
    private var onDifferentPinCodes: () -> Unit = {}

    /**
     * Коллбэк завершения ввода первого пин-кода
     * */
    fun setOnFirstStepComplete(listener: () -> Unit) {
        onFirstStepComplete = listener
    }

    /**
     * Коллбэк в случае разных пин-кодов
     * */
    fun setOnDifferentPinCodesListener(listener: () -> Unit) {
        onDifferentPinCodes = listener
    }

    private fun notifyFirstStepComplete() {
        onFirstStepComplete()
    }

    private fun notifyDifferentPinCodes() {
        onDifferentPinCodes()
    }

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

    /**
     * Установка первый пинкод
     * */
    private fun setFirstPinCode() {
        firstPinCode = pinCode.joinToString("")
        pinCode.clear()
        notifyFirstStepComplete()
    }

    private fun compareCodes() {
        when {
            firstPinCode == pinCode.joinToString("") -> {
                notifyCompleteListener()
            }
            else -> {
                firstPinCode = ""
                pinCode.clear()
                notifyDifferentPinCodes()
            }
        }
    }

    /**
     * Слушатель переполнения пин-кода
     * */
    override fun onCrowded() {
        vibrate(context)
        notifyCrowdedListener()
    }

    /**
     * Слушатель отсутствия первого элемента, при его удалении в пин-коде
     * */
    override fun onEmpty() {
        vibrate(context)
        notifyEmptyListener()
    }
}
