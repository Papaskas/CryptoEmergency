package com.cryptoemergency.cryptoemergency.lib.validation

/**
 * Функция для валидации текста с использованием одного или нескольких валидаторов
 *
 * @param text [String] Валидируемый текст
 * @param validators [Validator] Набор валидаторов текста
 *
 * @sample ValidationSamples
 */
fun validation(
    text: String,
    validators: List<Validator>,
    onResult: (hasError: Boolean, errorMessage: String?) -> Unit,
) {
    for (validator in validators) {
        if (!text.contains(validator.first)) {
            onResult(true, validator.second)
        }
    }

    onResult(false, null)
}
