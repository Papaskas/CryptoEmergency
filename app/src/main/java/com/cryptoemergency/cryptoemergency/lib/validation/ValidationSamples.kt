package com.cryptoemergency.cryptoemergency.lib.validation

/**
 * Примеры использования функции валидации.
 */
private object ValidationSamples {
    /**
     * Пример использования валидатора для проверки равенства строк.
     */
    fun isEquals() {
        validation(
            "Что сравнивать",
            listOf(
                ValidatorPatterns.isEquals(
                    toText = "С чем сравнивать",
                    errorMessage = "Не совпадают!",
                )
            )
        ) { hasError: Boolean, errorMessage: String? ->
            //...
        }
    }

    /**
     * Пример использования валидатора для проверки длины строки в диапазоне.
     */
    fun inRange() {
        validation(
            "Пример текста",
            listOf(ValidatorPatterns.inRange(min = 5, max = 10)),
        ) { hasError: Boolean, errorMessage: String? ->
            //...
        }
    }
}