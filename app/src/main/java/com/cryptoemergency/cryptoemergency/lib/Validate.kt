package com.cryptoemergency.cryptoemergency.lib

data class Validate(
    val pattern: Regex,
    val errorMessage: String,
    val successMessage: String? = null,
)

object ValidatePattern {
    val isEmail = Validate(
        Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)"),
        "Некорректная почта"
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

    val phoneNumber = Validate(
        Regex("^+?((d{2,3}) ?d|d)(([ -]?d)|( ?(d{2,3}) ?)){5,12}d$"),
        ""
    )

    val fullName = Validate(
        Regex("^[а-яА-ЯёЁa-zA-Z]+ [а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$"),
        ""
    )

    val domainName = Validate(
        Regex("^([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?.)+[a-zA-Z]{2,6}$"),
        ""
    )

    val URL = Validate(
        Regex("(https?):((//)|(\\\\))+[wd:#@%/;$()~_?+-=.&]*"),
        ""
    )

    val notSpaces = Validate(
        Regex("^\\S*\$"),
        "Пробелы недопустимы"
    )

    val notSpecialSymbol = Validate(
        Regex("^[a-zA-Z0-9]*\$"),
        "Спец символы недопустимы"
    )

    val IPv4 = Validate(
        Regex("((25[0-5]|2[0-4]d|[01]?dd?).){3}(25[0-5]|2[0-4]d|[01]?dd?)"),
        ""
    )

    val IPv6 = Validate(
        Regex("((^|:)([0-9a-fA-F]{0,4})){1,8}$"),
        ""
    )

    val UUID = Validate(
        Regex("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$"),
        ""
    )

    val notEmpty = Validate(
        Regex(".+"),
        "Не должно быть пустым"
    )

    fun inRange(min: Int, max: Int): Validate {
        return Validate(
            Regex("^.*${min},${max}}$"),
            "Пределы символов от $min до $max"
        )
    }
}

data class Return(
    val isError: Boolean,
    val errorMessage: String?,
    val successMessage: String?,
)

fun validation(
    text: String,
    vararg validators: Validate,
): Return {
    for (validator in validators) {
        if(text.contains(validator.pattern)) {
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
        successMessage = null,
    )
}

fun doublePasswordValidation(
    firstPassword: String,
    secondPassword: String,
): Return {
    val first = passwordValidation(firstPassword)
    val second = passwordValidation(secondPassword)

    return Return(
        isError = !first.isError && !second.isError,
        errorMessage = "Пароли не совпадают",
        successMessage = "Надежный пароль",
    )
}

fun passwordValidation(
    password: String,
): Return {
    return validation(
        password,
        ValidatePattern.hasUppercase,
        ValidatePattern.hasLowercase,
        ValidatePattern.hasDigit,
        ValidatePattern.hasSpecialChar,
        ValidatePattern.inRange(min = 8, max = 21),
    )
}

fun emailValidation(
    email: String
): Return {
    return validation(email, ValidatePattern.isEmail)
}
