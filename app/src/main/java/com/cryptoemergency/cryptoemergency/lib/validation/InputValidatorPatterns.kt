package com.cryptoemergency.cryptoemergency.lib.validation

/**
 * Обьект с готовыми валидаторами для текстовых полей
 * */
object InputValidatorPatterns {
    /**
     * Набор валидаторов для проверки пароля
     */
    val passwordPatterns = arrayListOf(
        ValidatorPatterns.notEmpty,
        ValidatorPatterns.withoutSpaces,
        ValidatorPatterns.hasUppercase,
        ValidatorPatterns.hasLowercase,
        ValidatorPatterns.hasDigit,
        ValidatorPatterns.hasSpecialChar,
        ValidatorPatterns.inRange(min = 8, max = 25),
    )

    /**
     * Валидатор двойного пароля
     *
     * @param equivalentTo Значение другого поля с паролем
     * @param errorMessage Сообщение в случае ошибки
     */
    fun doublePasswordPatterns(
        equivalentTo: String,
        errorMessage: String = "Пароли не совпадают",
    ): List<Validator> {
        passwordPatterns.add(ValidatorPatterns.isEquals(equivalentTo, errorMessage))

        return passwordPatterns
    }

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