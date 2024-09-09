package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import com.cryptoemergency.cryptoemergency.modifiers.commonBorder
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Базовый компонент Input с базовыми стилями, но без логики
 *
 * @param value значение вводимого текста, которое будет отображаться в текстовом поле
 * @param onValueChange Функция меняющая состояние вводимого текста
 * @param isEnabled управляет включенным состоянием этого текстового поля. При значении false этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param readOnly управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param disableActiveBorder Отключить обводку при фокусе
 * @param label метка, которая будет отображаться внутри контейнера текстового поля.
 * @param postLabel метка отображаемая в правом вверхнем углу, или же напротив label
 * @param placeholder необязательный заполнитель, который отображается, когда текстовое поле находится в фокусе, а
 * вводимый текст пуст
 * @param leadingIcon необязательный начальный значок, который будет отображаться в начале текстового поля
 * контейнер
 * @param trailingIcon необязательный завершающий значок, который будет отображаться в конце текстового поля
 * контейнер
 * @param prefix необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param supportingText необязательный вспомогательный текст, который будет отображаться под текстовым полем
 * @param isError указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * @param visualTransformation преобразует визуальное представление входных данных [value]
 * Например, вы можете использовать
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.Преобразование пароля] в
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 * @param aboveIcon Иконка имеющая абсолютное позиционирование над Input
 * @param keyboardOptions определяет параметры программной клавиатуры, которые содержат такие настройки
 * @param keyboardActions когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param singleLine при значении "true" это текстовое поле становится одним текстовым полем с горизонтальной прокруткой
 * вместо того, чтобы растягиваться на несколько строк. Клавиатуре будет сообщено, что клавиша возврата не отображается
 * в качестве [ImeAction]. Обратите внимание, что параметр [maxLines] будет проигнорирован, так как атрибуту
 * maxLines будет автоматически присвоено значение 1.
 * @param maxLines максимальная высота, выраженная в максимальном количестве видимых линий. Это обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param minLines минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param interactionSource указывает [MutableInteractionSource], представляющий поток [Interaction] с
 * для этого текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param colors Палитра цветов для Input
 * @param isFocused Выделено ли поле
 * @param contentAlignment Отношение позиционирования [aboveIcon] к [Input]
 * @param shape определяет форму контейнера этого текстового поля
 * @param cursorBrush Кисть для рисования курсора
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: MutableState<TextFieldValue>,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    isRequired: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    postLabel: @Composable ((TextStyle) -> Unit)? = null,
    aboveIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocused: Boolean = interactionSource.collectIsFocusedAsState().value,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    contentAlignment: Alignment = Alignment.TopStart,
    shape: Shape = RoundedCornerShape(Theme.dimens.shape),
    cursorBrush: Brush = SolidColor(if (isError) Theme.colors.error else Theme.colors.text1),
) {
    val labelStyle = if (value.value.text.isNotEmpty() || isFocused) {
        Theme.typography.caption2
    } else {
        Theme.typography.body1
    }

    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
    ) {
        BasicTextField(
            value = value.value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            cursorBrush = cursorBrush,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            textStyle = Theme.typography.body1.copy(
                color = Theme.colors.text1
            ),
            singleLine = singleLine,
            modifier = Modifier.commonBorder(
                shape = shape,
                isError = isError,
                isFocused = isFocused,
            ).fillMaxWidth(),
            decorationBox = {
                TextFieldDefaults.DecorationBox(
                    value = value.value.text,
                    innerTextField = it,
                    singleLine = singleLine,
                    enabled = isEnabled,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    isError = isError,
                    prefix = prefix,
                    suffix = suffix,
                    label = {
                        Row {
                            Label(label, isRequired, labelStyle)

                            postLabel?.let {
                                Spacer(Modifier.weight(1f))
                                postLabel(labelStyle)
                            }
                        }
                    },
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    shape = shape,
                    colors = colors.copy(
                        focusedTextColor = Theme.colors.text1,
                        unfocusedTextColor = Theme.colors.text1,
                        errorTextColor = Theme.colors.error,

                        focusedContainerColor = Theme.colors.surface1,
                        unfocusedContainerColor = Theme.colors.surface1,
                        errorContainerColor = Theme.colors.surface1,

                        cursorColor = Theme.colors.accent,
                        errorCursorColor = Theme.colors.error,

                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,

                        focusedLabelColor = Theme.colors.text2,
                        unfocusedLabelColor = Theme.colors.text2,
                        disabledLabelColor = Theme.colors.text2,
                        errorLabelColor = Theme.colors.text2,
                    )
                )
            }
        )

        aboveIcon?.let {
            it()
        }
    }
}

@Composable
private fun Label(
    label: String,
    isRequired: Boolean,
    labelStyle: TextStyle,
) {
    val annotatedString = buildAnnotatedString {
        append(label)
        if (isRequired) {
            withStyle(style = SpanStyle(color = Theme.colors.error)) {
                append(" *")
            }
        }
    }

    Text(
        text = annotatedString,
        style = labelStyle,
    )
}
