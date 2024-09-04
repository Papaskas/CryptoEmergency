package com.cryptoemergency.cryptoemergency.ui.screens.pages.qrCode

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.DarkColors
import com.cryptoemergency.cryptoemergency.providers.theme.LightColors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor() : ViewModel() {
    val options = listOf(DarkColors.accent, LightColors.B100)
    val selectedOption = mutableStateOf(options[0])
}
