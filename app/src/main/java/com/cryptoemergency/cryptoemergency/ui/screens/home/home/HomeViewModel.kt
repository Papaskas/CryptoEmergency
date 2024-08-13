package com.cryptoemergency.cryptoemergency.ui.screens.home.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _exchangeRate = mutableStateOf("Курс валют 1 USD = 78.58 EUR Курс валют 1 Курс валют 1 USD = 78.58 EUR Курс валют 1 Курс валют 1 USD = 78.58 EUR Курс валют")
    val exchangeRate = _exchangeRate.value
}
