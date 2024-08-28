package com.cryptoemergency.cryptoemergency.lib

fun validation(
    text: String,
    successMessage: String,
    vararg validators: Validate,
): Return {
    for (validator in validators) {
        if(!text.contains(validator.pattern)) {
            return Return(
                isError = true,
                errorMessage = validator.errorMessage,
                successMessage = null,
            )
        }
    }

    return Return(
        isError = false,
        errorMessage = null,
        successMessage = successMessage,
    )
}

data class Return(
    val isError: Boolean,
    val errorMessage: String?,
    val successMessage: String?,
)

data class Validate(
    val pattern: Regex,
    val errorMessage: String,
)

object ValidatePattern {
    val isEmail = Validate(
        pattern = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)"),
        errorMessage = "Некорректная почта",
    )

    val hasUppercase = Validate(
        Regex("[A-Z]"),
        "Необходима хотя бы одна заглавная буква"
    )

    val hasLowercase = Validate(
        Regex("[a-z]"),
        "Необходима хотя бы одна строчная буква"
    )

    val hasDigit = Validate(
        Regex("\\d"),
        "Необходима хотя бы одна цифра"
    )

    val hasSpecialChar = Validate(
        Regex("[!@#\$%^&+=]"),
        "Необходим хотя бы один спец символ"
    )

    val onlyLetter = Validate(
        Regex("^[a-zA-Zа-яА-Я]*\$"),
        ""
    )

    val onlyNumber = Validate(
        Regex("^[0-9]+[0-9]*\$"),
        "Допустимы только цифры"
    )

    val isPhoneNumber = Validate(
        Regex("^+?((d{2,3}) ?d|d)(([ -]?d)|( ?(d{2,3}) ?)){5,12}d$"),
        ""
    )

    val isFullName = Validate(
        Regex("^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$"),
        ""
    )

    val isDomainName = Validate(
        Regex("^([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?.)+[a-zA-Z]{2,6}$"),
        ""
    )

    val isURL = Validate(
        Regex("(https?):((//)|(\\\\))+[wd:#@%/;$()~_?+-=.&]*"),
        ""
    )

    val withoutSpaces = Validate(
        Regex("^\\S*\$"),
        "Пробелы недопустимы"
    )

    val notSpecialSymbol = Validate(
        Regex("^[a-zA-Z0-9]*\$"),
        "Спец символы недопустимы"
    )

    val isIPv4 = Validate(
        Regex("((25[0-5]|2[0-4]d|[01]?dd?).){3}(25[0-5]|2[0-4]d|[01]?dd?)"),
        ""
    )

    val isIPv6 = Validate(
        Regex("((^|:)([0-9a-fA-F]{0,4})){1,8}$"),
        ""
    )

    val isUUID = Validate(
        Regex("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$"),
        ""
    )

    val notEmpty = Validate(
        Regex(".+"),
        "Не должно быть пустым"
    )

    fun inRange(min: Int, max: Int): Validate {
        return Validate(
            Regex("^.{$min,$max}$"),
            "Пределы количества символов от $min до $max"
        )
    }
}

val passwordPatterns = arrayOf(
    ValidatePattern.notEmpty,
    ValidatePattern.withoutSpaces,
    ValidatePattern.hasUppercase,
    ValidatePattern.hasLowercase,
    ValidatePattern.hasDigit,
    ValidatePattern.hasSpecialChar,
    ValidatePattern.inRange(min = 8, max = 25),
)

val emailPatterns = arrayOf(
    ValidatePattern.notEmpty,
    ValidatePattern.withoutSpaces,
    ValidatePattern.isEmail,
    ValidatePattern.inRange(min = 7, max = 35)
)

