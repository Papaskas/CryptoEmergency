package com.cryptoemergency.cryptoemergency.lib.validation

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
//    fun doublePasswordPatterns(
//        confirmPassword: String,
//        errorMessage: String = "Пароли не совпадают",
//    ) = listOf(
//        ValidatorPatterns.notEmpty,
//        ValidatorPatterns.onlyLatin,
//        ValidatorPatterns.withoutSpaces,
//        ValidatorPatterns.hasLowercase,
//        ValidatorPatterns.hasUppercase,
//        ValidatorPatterns.hasDigit,
//        ValidatorPatterns.hasSpecialChar,
//        ValidatorPatterns.inRange(min = 8, max = 25),
//        ValidatorPatterns.isEquals(confirmPassword, errorMessage)
//    )

    fun doublePasswordPatterns(
        confirmPassword: String,
        errorMessage: String = "Пароли не совпадают",
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