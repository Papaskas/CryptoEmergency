package com.cryptoemergency.cryptoemergency.lib.validation

/**
 * Примеры использования функции валидации.
 */
object ValidatorSamples {
    private fun Sample() {
        val text = "checked text"
        val validators = listOf(
            ValidatorPatterns.notEmpty,
            ValidatorPatterns.withoutSpaces,
            ValidatorPatterns.hasDigit,
            ValidatorPatterns.hasUppercase,
        )

        validators.forEach {
            val errorMessage: String? = it.execute(text)
        }
    }

    /**
     * Пример использования валидатора для проверки равенства строк.
     */
    private fun isEquals() {
        val errorMessage: String? = ValidatorPatterns.isEquals(
            "С чем сравнить",
            "Строки не равны!"
        ).execute("Что сравнить")
    }

    /**
     * Пример использования валидатора для проверки длины строки в диапазоне.
     */
    private fun inRange() {
        val errorMessage: String? = ValidatorPatterns.inRange(
            0,
            10,
            "Не правильный диапозон!"
        ).execute("Что сравнить")
    }
}