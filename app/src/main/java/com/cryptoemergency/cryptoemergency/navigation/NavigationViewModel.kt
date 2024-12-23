package com.cryptoemergency.cryptoemergency.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.useCases.local.token.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    val token = MutableStateFlow<TokenEntity?>(null)

    init {
        viewModelScope.launch {
            token.value = getTokenUseCase()
        }
    }
}