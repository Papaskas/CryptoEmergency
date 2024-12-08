package com.cryptoemergency.cryptoemergency.lib.validation

import androidx.annotation.StringRes
import com.cryptoemergency.cryptoemergency.R

/**
 * Объект, содержащий предопределенные валидаторы. По своей сути является набором метаданных для
 * класса [Validator]
 *
 * @sample ValidatorSamples
 */
object ValidatorPatterns {
    private const val SPECIAL_CHARACTERS = "!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?"
    private const val DIGITS = "\\d"
    private const val LATIN_UPPER = "A-Z"
    private const val LATIN_LOWER = "a-z"
    private const val LATIN_ALPHABET = "$LATIN_UPPER$LATIN_LOWER"

    val isEmail = Validator(
        Regex("^[$LATIN_ALPHABET](.*)(@)(.+)(\\.)(.+)"),
        R.string.error_validation__is_email,
    )

    val hasUppercase = Validator(
        Regex("[$LATIN_UPPER]"),
        R.string.error_validation__has_uppercase,
    )

    val hasLowercase = Validator(
        Regex("[$LATIN_LOWER]"),
        R.string.error_validation__has_lowercase,
    )

    val hasDigit = Validator(
        Regex(DIGITS),
        R.string.error_validation__has_digit,
    )

    val hasSpecialChar = Validator(
        Regex("[$SPECIAL_CHARACTERS]"),
        R.string.error_validation__has_special_char,
    )

    val onlyLetter = Validator(
        Regex("^[a-zA-Zа-яА-Я]*\$"),
        R.string.error_validation__only_letter,
    )

    val onlyNumber = Validator(
        Regex("^[0-9]+[0-9]*\$"),
        R.string.error_validation__only_number,
    )

    val withoutSpaces = Validator(
        Regex("^\\S*\$"),
        R.string.error_validation__without_spaces,
    )

    val withoutSpecialSymbol = Validator(
        Regex("^[^$SPECIAL_CHARACTERS]+$"),
        R.string.error_validation__without_special_symbol,
    )

    val notEmpty = Validator(
        Regex(".+"),
        R.string.error_validation__not_empty,
    )

    /**
     * Не пропускает все кроме цифр, спец символов и английского алфавита
     * */
    val onlyLatin = Validator(
        Regex("^[$LATIN_ALPHABET$DIGITS$SPECIAL_CHARACTERS]+$"),
        R.string.error_validation__only_latin,
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
    fun isEquals(
        toText: String,
        @StringRes errorMessage: Int = R.string.error_validation__is_equals,
    ) = Validator(
        Regex("^${Regex.escape(toText)}$"),
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
     * @sample [ValidatorSamples.inRange]
     */
    fun inRange(
        min: Int,
        max: Int,
        @StringRes errorMessage: Int = R.string.error_validation__in_range
    ) = Validator(
        Regex("^.{$min,$max}$"),
        errorMessage,
    )
}
