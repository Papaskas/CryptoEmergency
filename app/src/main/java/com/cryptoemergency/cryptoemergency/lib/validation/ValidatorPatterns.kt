package com.cryptoemergency.cryptoemergency.lib.validation

/**
 * Объект, содержащий предопределенные валидаторы
 */
object ValidatorPatterns {
    val isEmail = Validator(
        Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)"),
        "Некорректная почта",
    )

    val hasUppercase = Validator(
        Regex("[A-Z]"),
        "Необходима хотя бы одна заглавная буква"
    )

    val hasLowercase = Validator(
        Regex("[a-z]"),
        "Необходима хотя бы одна строчная буква"
    )

    val hasDigit = Validator(
        Regex("\\d"),
        "Необходима хотя бы одна цифра"
    )

    val hasSpecialChar = Validator(
        Regex("[!@#\$%^&+=]"),
        "Необходим хотя бы один спец символ"
    )

    val onlyLetter = Validator(
        Regex("^[a-zA-Zа-яА-Я]*\$"),
        ""
    )

    val onlyNumber = Validator(
        Regex("^[0-9]+[0-9]*\$"),
        "Допустимы только цифры"
    )

    val isPhoneNumber = Validator(
        Regex("^+?((d{2,3}) ?d|d)(([ -]?d)|( ?(d{2,3}) ?)){5,12}d$"),
        ""
    )

    val isFullName = Validator(
        Regex("^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$"),
        ""
    )

    val isDomainName = Validator(
        Regex("^([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?.)+[a-zA-Z]{2,6}$"),
        ""
    )

    val isURL = Validator(
        Regex("(https?):((//)|(\\\\))+[wd:#@%/;$()~_?+-=.&]*"),
        ""
    )

    val withoutSpaces = Validator(
        Regex("^\\S*\$"),
        "Пробелы недопустимы"
    )

    val notSpecialSymbol = Validator(
        Regex("^[a-zA-Z0-9]*\$"),
        "Спец символы недопустимы"
    )

    val isIPv4 = Validator(
        Regex("((25[0-5]|2[0-4]d|[01]?dd?).){3}(25[0-5]|2[0-4]d|[01]?dd?)"),
        ""
    )

    val isIPv6 = Validator(
        Regex("((^|:)([0-9a-fA-F]{0,4})){1,8}$"),
        ""
    )

    val isUUID = Validator(
        Regex("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$"),
        ""
    )

    val notEmpty = Validator(
        Regex(".+"),
        "Не должно быть пустым"
    )

    /**
     * Валидатор для проверки равенства строк.
     *
     * @param toText Текст, с которым нужно сравнить.
     * @param errorMessage Сообщение об ошибке, если строки не совпадают.
     * @return [Validator] для проверки равенства строк.
     *
     * @sample [ValidationSamples.isEquals]
     */
    fun isEquals(
        toText: String,
        errorMessage: String = "Строки не совпадают!"
    ) = Validator(
        Regex.escape(toText).toRegex(),
        errorMessage,
    )

    /**
     * Валидатор для проверки длины строки в заданном диапазоне
     *
     * @param min Минимальная длина строки
     * @param max Максимальная длина строки
     * @param errorMessage Сообщение об ошибке, если длина строки не в диапазоне
     *
     * @return [Validator] для проверки длины строки.
     *
     * @sample [ValidationSamples.inRange]
     */
    fun inRange(
        min: Int,
        max: Int,
        errorMessage: String = "Некорректный диапазон символов, необходимо минимум $min до $max"
    ) = Validator(
        Regex("^.{$min,$max}$"),
        errorMessage,
    )
}
