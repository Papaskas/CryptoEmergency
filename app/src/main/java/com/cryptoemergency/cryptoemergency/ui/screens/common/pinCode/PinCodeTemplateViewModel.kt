package com.cryptoemergency.cryptoemergency.ui.screens.common.pinCode

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

abstract class PinCodeTemplateViewModel : ViewModel() {
    protected var pinCode = mutableStateListOf<Int>()

    abstract fun onComplete()
    abstract fun onClickIntButton()
    abstract fun onClickLeftButton()
    abstract fun onClickRightButton()
}
