package com.cryptoemergency.cryptoemergency.providers.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.data.store.ProtoDataStoreImpl
import com.cryptoemergency.cryptoemergency.api.domain.model.store.data.CurrentTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeProtoStore: ProtoDataStoreImpl<CurrentTheme>,
) : ViewModel() {

    /**
     * Получение темы из хранилища
     * */
    suspend fun getThemeFromStorage() = themeProtoStore.get()

    /**
     * Переключение темы. С темного на светлый и обратно
     */
    fun toggleTheme() {
        currentTheme = when (currentTheme) {
            CurrentTheme.DARK -> CurrentTheme.LIGHT
            CurrentTheme.LIGHT -> CurrentTheme.DARK
            CurrentTheme.NULL -> CurrentTheme.LIGHT
        }

        viewModelScope.launch(Dispatchers.IO) {
            themeProtoStore.put(currentTheme)
        }
    }

    /**
     * Смена темы на любую
     */
    fun changeTheme(theme: CurrentTheme) {
        currentTheme = theme

        viewModelScope.launch(Dispatchers.IO) {
            themeProtoStore.put(theme)
        }
    }
}

