package com.cryptoemergency.cryptoemergency.providers.locale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.cryptoemergency.cryptoemergency.providers.locale.langs.englishLang
import com.cryptoemergency.cryptoemergency.providers.locale.langs.russianLang

val LocalLocale = staticCompositionLocalOf<Lang> { error("No localLang provided") }

@Composable
fun LocalLocaleProvider(content: @Composable () -> Unit) {
    val locale = LocalContext.current.resources.configuration.locales[0]

    val lang = remember(locale) {
        when (locale.language) {
            "en" -> englishLang
            "ru" -> russianLang
            else -> russianLang
        }
    }

    CompositionLocalProvider(LocalLocale provides lang) {
        content()
    }
}
