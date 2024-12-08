package com.cryptoemergency.cryptoemergency.lib.validation

import androidx.annotation.StringRes
import com.cryptoemergency.cryptoemergency.R

/**
 * Обьект с готовыми валидаторами для текстовых полей
 *
 * @sample ValidatorSamples
 * */
object ValidatorInputPatterns {
    /**
     * Набор валидаторов для проверки пароля
     */
    val passwordPatterns = listOf(
        ValidatorPatterns.notEmpty,
        ValidatorPatterns.onlyLatin,
        ValidatorPatterns.withoutSpaces,
        ValidatorPatterns.hasLowercase,
        ValidatorPatterns.hasUppercase,
        ValidatorPatterns.hasDigit,
        ValidatorPatterns.hasSpecialChar,
        ValidatorPatterns.inRange(min = 8, max = 25),
    )

    /**
     * Валидатор двойного пароля
     *
     * @param confirmPassword Значение другого пароля
     * @param errorMessage Сообщение в случае ошибки
     */
    fun doublePasswordPatterns(
        confirmPassword: String,
        @StringRes errorMessage: Int = R.string.error_validation__password_not_equals,
    ) = passwordPatterns.plus(ValidatorPatterns.isEquals(confirmPassword, errorMessage))

    /**
     * Набор валидаторов для проверки email
     */
    val emailPatterns = listOf(
        ValidatorPatterns.notEmpty,
        ValidatorPatterns.withoutSpaces,
        ValidatorPatterns.isEmail,
        ValidatorPatterns.inRange(min = 7, max = 35)
    )
}