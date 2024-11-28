package com.cryptoemergency.cryptoemergency.lib.validation

import com.cryptoemergency.cryptoemergency.ui.common.inputs.InputSamples

/**
 * @param validator Паттерн валидации
 * @param errorMessage Сообщение об ошибке
 *
 * @sample ValidatorSamples
 * @sample InputSamples.ValidatorInputSample
 * */
open class Validator(
    private val validator: Regex,
    private val errorMessage: String,
) {
    /**
     * Функция валидации
     *
     * @return Сообщение [String] об ошибке если есть, или "null"
     * */
    open fun execute(text: String): String? {
        return if (!text.contains(validator)) {
            errorMessage
        } else {
            null
        }
    }
}
