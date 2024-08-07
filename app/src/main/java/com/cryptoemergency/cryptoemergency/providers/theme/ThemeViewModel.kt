package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.store.ProtoStore
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeProtoStore: ProtoStore<CurrentTheme>
) : ViewModel() {
    fun getTheme() {
        viewModelScope.launch {
            currentTheme = themeProtoStore.get()
        }
    }
}
