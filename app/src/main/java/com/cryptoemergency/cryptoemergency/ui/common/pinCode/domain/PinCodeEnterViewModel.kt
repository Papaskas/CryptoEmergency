package com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    /**
     * Слушатель завершения ввода пин-кода
     * */
    override fun onComplete() {
        notifyCompleteListener()
    }

    /**
     * Слушатель переполнения пин-кода
     * */
    override fun onCrowded() {
        vibrate(context)
        notifyCrowdedListener()
    }

    /**
     * Слушатель отсутсвия первого элемента при его удалении в пин-коде
     * */
    override fun onEmpty() {
        vibrate(context)
        notifyEmptyListener()
    }
}
