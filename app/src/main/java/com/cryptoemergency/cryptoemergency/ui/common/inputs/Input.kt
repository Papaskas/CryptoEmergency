package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import com.cryptoemergency.cryptoemergency.modifiers.commonBorder
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme

/**
 * Базовый компонент Input с базовыми стилями, но без логики
 *
 * @param value [TextFieldValue] Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param label [String] Метка, которая будет отображаться внутри контейнера текстового поля
 * @param modifier [Modifier] Модификатор применяемый к к разметке текстового поля
 * @param onValueChange Функция меняющая состояние вводимого текста
 * @param hasError [Boolean] Указывает, является ли текущее значение текстового поля ошибочным. Если
 * установлено значение true, то текстовое поле будет окрашено цветом ошибки
 * @param isEnabled [Boolean] Управляет включенным состоянием этого текстового поля. При значении false
 * этот компонент не будет реагировать на ввод данных пользователем, и оно будет выглядеть визуально
 * отключенным и недоступным.
 * @param readOnly [Boolean] Управляет состоянием текстового поля, доступного для редактирования. При
 * значении "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него.
 * @param isRequired [Boolean] Добавляет значок обязательного текстового поля. Обратите внимание что
 * это определяет только визуальный стиль, программно это никак не запрещает не вводить значение
 * @param singleLine [Boolean] При значении "true" это текстовое поле становится одним текстовым полем
 * с горизонтальной прокруткой вместо того, чтобы растягиваться на несколько строк. Клавиатуре будет сообщено,
 * что клавиша возврата не отображается в качестве [ImeAction]. Обратите внимание, что параметр [maxLines]
 * будет проигнорирован, так как атрибуту [maxLines] будет автоматически присвоено значение 1.
 * @param minLines [Int] Минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param maxLines [Int] Максимальная высота, выраженная в максимальном количестве видимых линий. Это обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param aboveIcon [Composable] Метка с абсолютным позиционированием над текстовым полем. Позицию
 * задавать с помощью [Alignment] на самого себя
 * @param leadingIcon [Composable] Необязательный начальный значок, который будет отображаться в начале
 * текстового поля контейнер
 * @param trailingIcon [Composable] Необязательный завершающий значок, который будет отображаться в
 * конце текстового поля контейнер
 * @param prefix [Composable] Необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix [Composable] Необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param container [Composable] Контейнер, который будет отображаться за текстовым полем
 * @param visualTransformation [VisualTransformation] Преобразует визуальное представление входных данных [value]
 * @param keyboardOptions [KeyboardOptions] Определяет параметры программной клавиатуры
 * @param keyboardActions [KeyboardActions] Коллбэки событий. Эти действия могут отличаться от того,
 * что вы указано в [KeyboardOptions.imeAction]
 * @param interactionSource [MutableInteractionSource], представляет поток [Interaction] для этого
 * текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param isFocused [Boolean] Выделено ли поле
 * @param colors [TextFieldColors] Палитра цветов для Input
 * @param shape [Shape] Для контейнера текстового поля
 * @param cursorBrush [Brush] Обводка текстового поля
 *
 * @sample InputSamples.InputSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: MutableState<TextFieldValue>,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    hasError: Boolean = false,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    isRequired: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    aboveIcon: @Composable BoxScope.() -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    container: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocused: Boolean = interactionSource.collectIsFocusedAsState().value,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    shape: Shape = RoundedCornerShape(Theme.dimens.radius),
    cursorBrush: Brush = SolidColor(if (hasError) Theme.colors.error else Theme.colors.text1),
) {
    Box(modifier = modifier) {
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
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            modifier = Modifier
                .commonBorder(
                    shape = shape,
                    isError = hasError,
                    isFocused = isFocused,
                )
                .fillMaxWidth(),
            decorationBox = {
                TextFieldDefaults.DecorationBox(
                    value = value.value.text,
                    innerTextField = it,
                    singleLine = singleLine,
                    enabled = isEnabled,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    isError = hasError,
                    prefix = prefix,
                    suffix = suffix,
                    label = {
                        Label(
                            label,
                            isRequired,
                            value.value.text.isNotEmpty(),
                            isFocused,
                        )
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
                    ),
                    container = container,
                )
            }
        )

        aboveIcon()
    }
}

/**
 * Заголовок текстового поля
 *
 * @param label [String] Сам заголовок
 * @param isRequired [Boolean] Добавляет индикацию обязательного текстового поля
 * @param textIsNotEmpty [Boolean] Нужен для логики стилизации
 * @param isFocused [Boolean] Нужен для логики стилизации
 * */
@Composable
private fun Label(
    label: String,
    isRequired: Boolean,
    textIsNotEmpty: Boolean,
    isFocused: Boolean,
) {
    val labelStyle = if (textIsNotEmpty || isFocused) {
        Theme.typography.caption2
    } else {
        Theme.typography.body1
    }

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
