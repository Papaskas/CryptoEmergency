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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.validation.InputValidatorPatterns
import com.cryptoemergency.cryptoemergency.lib.validation.Validator
import com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput.ValidatorInput

/**
 * Поле ввода с логикой пароля. Наследуется от [ValidatorInput]
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
 * @param hasError указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * @param keyboardOptions определяет параметры программной клавиатуры, которые содержат такие настройки, как
 * [KeyboardType] и [ImeAction].
 * @param keyboardActions когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param interactionSource указывает [MutableInteractionSource], представляющий поток [Interaction] с
 * для этого текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param validators [Validator] Список правил валидирования
 */
@Composable
fun PasswordInput(
    value: MutableState<TextFieldValue>,
    hasError: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    showIconVisible: Boolean = true,
    passwordVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    validators: List<Validator> = InputValidatorPatterns.passwordPatterns,
) {
    val image = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off

    val res = LocalContext.current.resources
    val description = if (passwordVisible.value) {
        res.getString(R.string.hide, res.getString(R.string.password))
    } else {
        res.getString(R.string.show, res.getString(R.string.password))
    }

    ValidatorInput(
        modifier = modifier,
        value = value,
        readOnly = readOnly,
        label = res.getString(R.string.password),
        hasError = hasError,
        isRequired = true,
        trailingIcon = {
            if (showIconVisible) {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        painter = painterResource(image),
                        contentDescription = description,
                    )
                }
            }
        },
        isEnabled = isEnabled,
        maxLines = 1,
        minLines = 1,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Password,
        ),
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        validators = validators
    )
}
