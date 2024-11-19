package com.cryptoemergency.cryptoemergency.providers.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.store.ProtoStore
import com.cryptoemergency.cryptoemergency.repository.store.ProtoKeys
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme
import com.cryptoemergency.cryptoemergency.ui.common.inputs.DateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
open class ThemeViewModel @Inject constructor(
    private val themeProtoStore: ProtoStore<CurrentTheme>,
) : ViewModel() {
    suspend fun getThemeFromStorage() = themeProtoStore.get()

    open fun toggleTheme() {
        currentTheme = when (currentTheme) {
            CurrentTheme.DARK -> CurrentTheme.LIGHT
            CurrentTheme.LIGHT -> CurrentTheme.DARK
            CurrentTheme.NULL -> CurrentTheme.LIGHT
        }

        viewModelScope.launch(Dispatchers.IO) {
            themeProtoStore.put(currentTheme)
        }
    }

    open fun changeTheme(theme: CurrentTheme) {
        currentTheme = theme

        viewModelScope.launch(Dispatchers.IO) {
            themeProtoStore.put(theme)
        }
    }
}

/**
 * Фейковый [ThemeViewModel] для [Preview], в тех случаях когда необходимо использовать [ThemeProvider]
 *
 * @param context Контекст приложения. Нужен для получения [store]
 * @param theme Тема приложения. По умолчанию [CurrentTheme.DARK]
 * @param store Экземпляр хранилища для [ThemeViewModel]. Не передавать явно
 *
 * @sample SamplePreview
 **/
class FakeThemeViewModel(
    private val context: Context,
    private val theme: CurrentTheme = CurrentTheme.DARK,
    private val store: ProtoStore<CurrentTheme> = ProtoStore(ProtoKeys.THEME, context),
) : ThemeViewModel(store) {

    //override suspend fun getThemeFromStorage() = currentTheme

    init {
        currentTheme = theme
    }

    override fun toggleTheme() {
        currentTheme = when (currentTheme) {
            CurrentTheme.DARK -> CurrentTheme.LIGHT
            CurrentTheme.LIGHT -> CurrentTheme.DARK
            CurrentTheme.NULL -> CurrentTheme.LIGHT
        }
    }

    override fun changeTheme(theme: CurrentTheme) {
        currentTheme = theme
    }
}

@Composable
private fun SamplePreview() {
    val context = LocalContext.current
    val vm: ThemeViewModel = FakeThemeViewModel(context)

    val value = remember { mutableStateOf(TextFieldValue()) }

    ThemeProvider(vm) {
        DateInput(
            value = value,
            label = "Дата рождения",
        )
    }
}
