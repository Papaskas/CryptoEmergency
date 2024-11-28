package com.cryptoemergency.cryptoemergency.lib.validation

import com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput.ValidatorInput

/**
 * Объект, содержащий предопределенные валидаторы. По своей сути является набором метаданных для
 * функции [validation]
 *
 * Сравнимаемый текст сам передается в функцию [validation] в [ValidatorInput]
 */
object ValidatorPatterns {
    private const val SPECIAL_CHARACTERS = "!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?"
    private const val DIGITS = "\\d"
    private const val LATIN_UPPER = "A-Z"
    private const val LATIN_LOWER = "a-z"
    private const val LATIN_ALPHABET = "$LATIN_UPPER$LATIN_LOWER"

    val isEmail = Validator(
        Regex("^[$LATIN_ALPHABET](.*)(@)(.+)(\\.)(.+)"),
        "Некорректная почта",
    )

    val hasUppercase = Validator(
        Regex("[$LATIN_UPPER]"),
        "Необходима хотя бы одна заглавная буква"
    )

    val hasLowercase = Validator(
        Regex("[$LATIN_LOWER]"),
        "Необходима хотя бы одна строчная буква"
    )

    val hasDigit = Validator(
        Regex(DIGITS),
        "Необходима хотя бы одна цифра"
    )

    val hasSpecialChar = Validator(
        Regex("[$SPECIAL_CHARACTERS]"),
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
     * Не пропускает все кроме цифр, спец символов и английского алфавита
     * */
    val onlyLatin = Validator(
        Regex("^[$LATIN_ALPHABET$DIGITS$SPECIAL_CHARACTERS]+$"),
        "Только латинские буквы"
    )

    /**
     * Валидатор для проверки равенства строк.
     *
     * @param toText [String] Текст, с которым нужно сравнить.
     * @param errorMessage [String] Сообщение об ошибке, если строки не совпадают.
     * @return [Validator] для проверки равенства строк.
     *
     * @sample [ValidatorSamples.isEquals]
     */
    class IsEquals(
        private val toText: String,
        private val errorMessage: String = "Строки не совпадают!"
    ) : Validator(Regex(""), errorMessage) {
        override fun execute(text: String): String? {
            return if (text != toText) {
                errorMessage
            } else {
                null
            }
        }
    }

    /**
     * Валидатор для проверки длины строки в заданном диапазоне
     *
     * @param min Минимальная длина строки
     * @param max Максимальная длина строки
     * @param errorMessage Сообщение об ошибке, если длина строки не в диапазоне
     *
     * @return [Validator] для проверки длины строки.
     *
     * @sample [ValidatorSamples.inRange]
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
