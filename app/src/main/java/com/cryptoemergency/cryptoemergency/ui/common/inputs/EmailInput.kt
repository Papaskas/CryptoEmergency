package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.lib.emailPatterns

/**
 * Комопнент Input с логикой Email. Наследуется от ValidateInput
 *
 * @param value значение вводимого текста, которое будет отображаться в текстовом поле
 * @param modifier [Modifier], который должен быть применен к этому текстовому полю.
 * @param isEnabled управляет включенным состоянием этого текстового поля. При значении "false" этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param readOnly управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param isError указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 */
@Composable
fun EmailInput(
    value: MutableState<TextFieldValue>,
    isError: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
) {
    ValidateInput(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        label = "Email",
        isError = isError,
        isEnabled = isEnabled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
        ),
        validators = emailPatterns,
    )
}
