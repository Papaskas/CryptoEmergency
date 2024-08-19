package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.providers.theme.currentTheme
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme

/**
 * Базовый компонент Input с базовыми стилями, но без логики
 *
 * @param value значение вводимого текста, которое будет отображаться в текстовом поле
 * @param modifier [Modifier], который должен быть применен к этому текстовому полю.
 * @param enabled управляет включенным состоянием этого текстового поля. При значении "false" этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param readOnly управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param label - необязательная метка, которая будет отображаться внутри контейнера текстового поля.
 * @param placeholder - необязательный заполнитель, который отображается, когда текстовое поле находится в фокусе, а
 * вводимый текст пуст
 * @param leadingIcon - необязательный начальный значок, который будет отображаться в начале текстового поля
 * контейнер
 * @param trailingIcon - необязательный завершающий значок, который будет отображаться в конце текстового поля
 * контейнер
 * @param prefix - необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix - необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param supportingText - необязательный вспомогательный текст, который будет отображаться под текстовым полем
 * @param isError указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * @param visualTransformation преобразует визуальное представление входных данных [value]
 * Например, вы можете использовать
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.Преобразование пароля] в
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 * @param keyboardOptions определяет параметры программной клавиатуры, которые содержат такие настройки, как
 * [keyboardType] и [ImeAction].
 * @param keyboardActions когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param singleLine при значении "true" это текстовое поле становится одним текстовым полем с горизонтальной прокруткой
 * вместо того, чтобы растягиваться на несколько строк. Клавиатуре будет сообщено, что клавиша возврата не отображается
 * в качестве [ImeAction]. Обратите внимание, что параметр [maxLines] будет проигнорирован, так как атрибуту
 * maxLines будет автоматически присвоено значение 1.
 * @param maxLines - максимальная высота, выраженная в максимальном количестве видимых линий. Это обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param minLines - минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param interactionSource указывает [MutableInteractionSource], представляющий поток [Interaction] с
 * для этого текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseInput(
    value: MutableState<TextFieldValue>,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val isFocused = interactionSource.collectIsFocusedAsState()
    val borderModifier = if (isFocused.value) {
        modifier.border(1.dp, Theme.colors.accent, RoundedCornerShape(10.dp))
    } else if (currentTheme == CurrentTheme.DARK) {
        modifier.border(1.dp, Theme.colors.stroke, RoundedCornerShape(10.dp))
    } else {
        modifier
    }

    BasicTextField(
        value = value.value,
        readOnly = readOnly,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        minLines = minLines,
        onValueChange = { value.value = it },
        interactionSource = interactionSource,
        enabled = enabled,
        textStyle = Theme.typography.body1,
        singleLine = singleLine,
        modifier = borderModifier.fillMaxWidth()
    ) {
        TextFieldDefaults.DecorationBox(
            value = value.value.text,
            innerTextField = it,
            singleLine = singleLine,
            enabled = enabled,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            prefix = {
                prefix?.let {
                    Text(text = prefix)
                }
            },
            suffix = {
                suffix?.let {
                    Text(text = suffix)
                }
            },
//            supportingText = { TODO: Вызывает, пока оставить до Validate логики
//                supportingText?.let {
//                    Text(text = supportingText)
//                }
//            },
            label = {
                Text(
                    text = label,
                    style = if (isFocused.value) {
                        Theme.typography.caption2
                    } else {
                        Theme.typography.body1
                    }
                )
            },
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Theme.colors.text1,
                unfocusedTextColor = Theme.colors.text1,
                errorTextColor = Theme.colors.error,

//                focusedContainerColor = Theme.colors.surface2,
//                unfocusedContainerColor = Theme.colors.surface,
//                errorContainerColor = Theme.colors.surface,

                cursorColor = Theme.colors.accent,
                errorCursorColor = Theme.colors.error,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,

                //            focusedLeadingIconColor = focusedLeadingIconColor,
                //            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
                //            disabledLeadingIconColor = disabledLeadingIconColor,
                //            errorLeadingIconColor = errorLeadingIconColor,
                //
                //            focusedTrailingIconColor = focusedTrailingIconColor,
                //            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
                //            disabledTrailingIconColor = disabledTrailingIconColor,
                //            errorTrailingIconColor = errorTrailingIconColor,
                //
                focusedLabelColor = Theme.colors.text2,
                unfocusedLabelColor = Theme.colors.text2,
                disabledLabelColor = Theme.colors.text2,
                errorLabelColor = Theme.colors.text2,
                //
                //            focusedSupportingTextColor = focusedSupportingTextColor,
                //            unfocusedSupportingTextColor = unfocusedSupportingTextColor,
                //            disabledSupportingTextColor = disabledSupportingTextColor,
                //            errorSupportingTextColor = errorSupportingTextColor,
                //
                //            focusedPrefixColor = focusedPrefixColor,
                //            unfocusedPrefixColor = unfocusedPrefixColor,
                //            disabledPrefixColor = disabledPrefixColor,
                //            errorPrefixColor = errorPrefixColor,
                //
                //            focusedSuffixColor = focusedSuffixColor,
                //            unfocusedSuffixColor = unfocusedSuffixColor,
                //            disabledSuffixColor = disabledSuffixColor,
                //            errorSuffixColor = errorSuffixColor,
            )
        )
    }
}
