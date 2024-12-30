package com.cryptoemergency.cryptoemergency.lib.validation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cryptoemergency.cryptoemergency.R

/**
 * Примеры использования функции валидации.
 */
object ValidatorSamples {
    @Composable
    private fun Sample() {
        val context = LocalContext.current
        val text = "checked text"
        val validators = listOf(
            ValidatorPatterns.notEmpty,
            ValidatorPatterns.withoutSpaces,
            ValidatorPatterns.hasDigit,
            ValidatorPatterns.hasUppercase,
        )

        validators.forEach {
            val errorMessage: String? = it.execute(text, context)
        }
    }

    /**
     * Пример использования валидатора для проверки равенства строк.
     */
    @Composable
    private fun isEquals() {
        val context = LocalContext.current

        val errorMessage: String? = ValidatorPatterns.isEquals(
            toText = "С чем сравнить",
            errorMessage = R.string.error_validation__is_equals
        ).execute("Что сравнить", context)
    }

    /**
     * Пример использования валидатора для проверки длины строки в диапазоне.
     */
    @Composable
    private fun inRange() {
        val context = LocalContext.current

        val errorMessage: String? = ValidatorPatterns.inRange(
            min = 0,
            max = 10,
            errorMessage = R.string.error_validation__in_range
        ).execute("Что сравнить", context)
    }
}