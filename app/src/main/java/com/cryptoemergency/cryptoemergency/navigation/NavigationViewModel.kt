package com.cryptoemergency.cryptoemergency.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaska.data.qualifiers.TokenStorage
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.useCases.storage.LocalStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    @TokenStorage private val tokenStorage: LocalStorageUseCase<TokenEntity>,
) : ViewModel() {
    private val _token = MutableStateFlow<TokenEntity?>(null)
    val token = _token.asStateFlow()

    init {
        viewModelScope.launch {
            _token.value = tokenStorage.get()
        }
    }
}