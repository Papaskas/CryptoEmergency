package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.lib.validation.InputValidatorPatterns.emailPatterns

/**
 * Комопнент [ValidatorInput] с валидаторами [emailPatterns]. Наследуется от [ValidatorInput]
 *
 * @param value [TextFieldValue] Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param hasError [Boolean] Указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 * @param modifier [Modifier] Применяемый к разметке текстового поля
 * @param isEnabled [Boolean] Управляет включенным состоянием этого текстового поля. При значении
 * "false" этот компонент не будет реагировать на ввод данных пользователем, и оно будет выглядеть
 * визуально отключенным и недоступным
 * @param readOnly [Boolean] Управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 *
 * @sample InputSamples.EmailInputSample
 */
@Composable
fun EmailInput(
    value: MutableState<TextFieldValue>,
    hasError: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
) {
    ValidatorInput(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        label = "Email",
        isRequired = true,
        hasError = hasError,
        isEnabled = isEnabled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
        ),
        validators = emailPatterns,
    )
}
