package com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.vibrate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Логика ВВОДА пин кода
 * */
@HiltViewModel
class PinCodeEnterViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : PinCodeViewModel() {
    private val _message = mutableStateOf<String?>(null)
    val message: State<String?> = _message

    /**
     * Слушатель завершения ввода пин-кода
     * */
    override fun onComplete() {
        _message.value = "Success"
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
