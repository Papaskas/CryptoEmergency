package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.lib.validation.InputValidatorPatterns
import com.cryptoemergency.cryptoemergency.lib.validation.ValidatorPatterns

/**
 * Группа компонентов [PasswordInput] с логикой двойного пароля. Наследуется от [PasswordInput]
 *
 * @param values [Pair] Значения вводимого текста, которые будут отображаться в текстовых полях
 * @param modifier [Modifier] применяемый к разметке текстовых полей
 * @param spacedBy [Dp] расстояние между полями ввода
 * @param isEnabled [Pair] управляет включенным состоянием этого текстового поля. При значении
 * "false" этот компонент не будет реагировать на ввод данных пользователем, и оно будет выглядеть
 * визуально отключенным и недоступным
 * @param readOnly [Pair] управляет состоянием текстового поля для редактирования. При значении
 * true текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него.
 * @param hasErrors [Pair] указывает, является ли текущее значение текстового поля ошибочным
 * @param keyboardOptions [KeyboardOptions] параметры клавиатуры
 * @param keyboardActions [Pair] коллбэки событий. Эти действия могут отличаться от того,
 * что вы указано в [KeyboardOptions.imeAction]
 * @param interactionSource [Pair] указывает, представляющий поток [Interaction] для каждого текстового поля.
 * Можно создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 *
 * @sample InputSamples.DoublePasswordSample
 */
@Composable
fun DoublePasswordsInput(
    values: Pair<MutableState<TextFieldValue>, MutableState<TextFieldValue>>,
    modifier: Modifier = Modifier,
    spacedBy: Dp = 15.dp,
    isEnabled: Pair<Boolean, Boolean> = Pair(true, true),
    readOnly: Pair<Boolean, Boolean> = Pair(false, false),
    hasErrors: MutableState<Boolean> = mutableStateOf(false),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: Pair<KeyboardActions, KeyboardActions> = Pair(
        KeyboardActions.Default,
        KeyboardActions.Default,
    ),
    interactionSource: Pair<MutableInteractionSource, MutableInteractionSource> = Pair(
        remember { MutableInteractionSource() },
        remember { MutableInteractionSource() },
    ),
) {
    val passwordVisible = remember { mutableStateOf(false) }

    Column(modifier) {
        PasswordInput(
            value = values.first,
            showValidatorMessage = false,
            hasError = hasErrors,
            isEnabled = isEnabled.first,
            readOnly = readOnly.first,
            keyboardOptions = keyboardOptions.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = keyboardActions.first,
            interactionSource = interactionSource.first,
            passwordVisible = passwordVisible,
            showIconVisible = false,
            validators = InputValidatorPatterns.doublePasswordPatterns(values.second.value.text),
        )
        Spacer(Modifier.height(spacedBy))
        PasswordInput(
            value = values.second,
            hasError = hasErrors,
            isEnabled = isEnabled.second,
            readOnly = readOnly.second,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions.second,
            interactionSource = interactionSource.second,
            passwordVisible = passwordVisible,
            validators = InputValidatorPatterns.doublePasswordPatterns(values.first.value.text)
        )
    }
}

