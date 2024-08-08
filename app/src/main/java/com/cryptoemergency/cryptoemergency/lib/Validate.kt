package com.cryptoemergency.cryptoemergency.lib

import androidx.compose.runtime.MutableState

fun validateEmail(
    email: String,
    errorMessage: MutableState<String?>,
    isError: MutableState<Boolean>,
) {
    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    isError.value = !email.matches(emailRegex)
    if (isError.value) {
        errorMessage.value = "Некоррекная почта"
    } else {
        errorMessage.value = null
    }
}

fun validatePassword(
    password: String,
    confirmPassword: String,
    errorMessage: MutableState<String?>,
    successMessage: MutableState<String?>,
    isError: MutableState<Boolean>,
) {
    val minLength = 8
    val hasUppercase = Regex("[A-Z]")
    val hasLowercase = Regex("[a-z]")
    val hasDigit = Regex("\\d")
    val hasSpecialChar = Regex("[!@#\$%^&+=]")

    val message =
        when {
            password.length < minLength -> "Пароль должен содержать не менее 8 символов"
            !password.contains(hasUppercase) -> "Пароль должен содержать хотя бы одну заглавную букву"
            !password.contains(hasLowercase) -> "Пароль должен содержать хотя бы одну строчную букву"
            !password.contains(hasDigit) -> "Пароль должен содержать хотя бы одну цифру"
            !password.contains(hasSpecialChar) -> "Пароль должен содержать хотя бы один спец символ"
            password != confirmPassword -> "Пароли не совпадают"
            else -> null
        }

    isError.value = message != null
    errorMessage.value = message

    if (message == null) {
        successMessage.value = "Надежный пароль"
    } else {
        successMessage.value = null
    }
}

// используется для числовых полей(денежных)
fun isValidNumber(value: String): Boolean {
    // проверка на то, что значение являесят числом и больше нуля
    return value.toDoubleOrNull()?.let { it > 0 } ?: false
}

fun validateOnlyLetters(
    text: String,
    errorMessage: MutableState<String?>,
    isError: MutableState<Boolean>,
) {
    if (!text.matches(Regex("^[a-zA-Zа-яА-Я]*$"))) {
        isError.value = true
        errorMessage.value = "Разрешенно использовать только буквы"
    } else {
        isError.value = false
        errorMessage.value = null
    }
}
