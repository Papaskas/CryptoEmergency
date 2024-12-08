package com.cryptoemergency.cryptoemergency.lib.validation

import android.content.Context
import androidx.annotation.StringRes
import com.cryptoemergency.cryptoemergency.ui.common.inputs.InputSamples

/**
 * Класс валидации. Принимает метаданные [validator] и [errorMessage], а внутренний метод [execute]
 * валидирует переданную строкую.
 *
 * Является родителем для [ValidatorPatterns]
 *
 * @param validator [Regex] Паттерн валидации
 * @param errorMessage [StringRes] Сообщение об ошибке
 *
 * @sample ValidatorSamples
 * @sample InputSamples.ValidatorInputSample
 * */
open class Validator(
    private val validator: Regex,
    @StringRes private val errorMessage: Int,
) {
    /**
     * Функция валидации
     *
     * @param text [String] Валидируемый текст
     * @param context [Context] Контекст приложения. Нужен для получения сообщения ошибки
     *
     * @return Сообщение [String] об ошибке если есть, или "null"
     * */
    open fun execute(
        text: String,
        context: Context,
    ): String? {
        return if (!text.contains(validator)) {
            context.resources.getString(errorMessage)
        } else {
            null
        }
    }
}
