package com.cryptoemergency.cryptoemergency.ui.screens.auth.changeProfileData

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeProfileDataViewModel @Inject constructor(

) : ViewModel() {
    val firstName = mutableStateOf(TextFieldValue(""))
    val username = mutableStateOf(TextFieldValue(""))
    val aboutMe = mutableStateOf(TextFieldValue(""))
    val specialization = mutableStateOf(TextFieldValue(""))
    val birthday = mutableStateOf(TextFieldValue(""))
    val language = mutableStateOf(TextFieldValue(""))
}
