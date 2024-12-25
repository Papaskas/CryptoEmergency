package com.cryptoemergency.cryptoemergency.ui.screens.pages.qrCode

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.darkPalette
import com.cryptoemergency.cryptoemergency.providers.theme.provides.palettes.lightPalette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor() : ViewModel() {
    val options = listOf(darkPalette.accent, lightPalette.text1)
    val selectedOption = mutableStateOf(options[0])
}
