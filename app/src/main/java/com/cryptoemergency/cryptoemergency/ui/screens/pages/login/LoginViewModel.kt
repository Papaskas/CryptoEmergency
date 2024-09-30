package com.cryptoemergency.cryptoemergency.ui.screens.pages.login

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {
    val currentStep = mutableIntStateOf(0)

    val emailInput = mutableStateOf(TextFieldValue())
    val emailError = mutableStateOf(false)
    val passwordInput = mutableStateOf(TextFieldValue())
    val passwordError = mutableStateOf(false)

    fun continueAsGuest() {

    }

    fun login() {

    }
}
