package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.passwordPatterns

/**
 * Компонент Input с логикой пароля. Наследуется от ValidateInput
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
 * @param keyboardOptions определяет параметры программной клавиатуры, которые содержат такие настройки, как
 * [keyboardType] и [ImeAction].
 * @param keyboardActions когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param interactionSource указывает [MutableInteractionSource], представляющий поток [Interaction] с
 * для этого текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 */
@Composable
fun PasswordInput(
    value: MutableState<TextFieldValue>,
    isError: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    passwordVisible: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    val context = LocalContext.current
    val image = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off
    val description = if (passwordVisible.value) {
        context.getString(R.string.hide, R.string.password)
    } else {
        context.getString(R.string.show, R.string.password)
    }

    ValidateInput(
        modifier = modifier,
        value = value,
        readOnly = readOnly,
        label = context.getString(R.string.password),
        isError = isError,
        // prefix = "prefix",
        // suffix = "suffix",
        trailingIcon = {
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    painter = painterResource(image),
                    contentDescription = description,
                )
            }
        },
        isEnabled = isEnabled,
        maxLines = 1,
        minLines = 1,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        validators = passwordPatterns
    )
}
