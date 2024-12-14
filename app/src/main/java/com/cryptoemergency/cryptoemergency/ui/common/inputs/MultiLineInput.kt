package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.validation.Validator
import com.cryptoemergency.cryptoemergency.lib.validation.ValidatorPatterns
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput.ValidatorInput

/**
 * Комопнент [Input] с логикой многостраничного ввода с паттерном минимальных и максимальных символов.
 * Наследуется от [ValidatorInput]
 *
 * @param value [TextFieldValue] Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param label [String] Метка, которая будет отображаться внутри контейнера текстового поля.
 * @param maxLines [Int] Максимальная высота, выраженная в максимальном количестве видимых линий. Это
 * обязательно что 1 <= [minLines] <= [maxLines].
 * @param minLines минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines].
 * @param minSymbols [Int] Минимальное количество символов
 * @param maxSymbols [Int] Максимальное количество символов
 * @param hasError [Boolean] Указывает, является ли текущее значение текстового поля ошибочным. Если
 * установлено значение true, то текстовое поле будет окрашено цветом ошибки
 * @param modifier [Modifier] Который должен быть применен к этому текстовому полю
 * @param isEnabled [Boolean] Управляет включенным состоянием этого текстового поля. При значении
 * false этот компонент будет не реагирует на ввод данных пользователем, и оно будет выглядеть
 * визуально отключенным и недоступным для доступа к сервисам.
 * @param readOnly [Boolean] Управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param onValueChange Коллбэк, когда служба ввода обновляет значения в текстовом поле [value]
 * @param isRequired [Boolean] Является данное ли поле обязательным
 * @param prefix [Composable] Необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix [Composable] Необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param visualTransformation [VisualTransformation] Преобразует визуальное представление входных данных [value]
 * @param keyboardOptions [KeyboardOptions] Определяет параметры программной клавиатуры
 * @param keyboardActions [KeyboardActions] коллбэки событий. Эти действия могут отличаться от того,
 * что вы указано в [KeyboardOptions.imeAction]
 * @param interactionSource [MutableInteractionSource], представляет поток [Interaction] для этого
 * текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param colors [TextFieldColors] Цветовое оформление в разных состояниях
 * @param validators [Validator] Параметры валидации, есть готовые в [ValidatorPatterns]
 *
 * @sample InputSamples.MultilineSample
 */
@Composable
fun MultiLineInput(
    value: MutableState<TextFieldValue>,
    label: String,
    maxLines: Int,
    minLines: Int,
    minSymbols: Int,
    maxSymbols: Int,
    hasError: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    isRequired: Boolean = false,
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.colors(),
    validators: List<Validator> = emptyList(),
) {
    ValidatorInput(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        singleLine = false,
        prefix = prefix,
        suffix = suffix,
        label = label,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        colors = colors,
        hasError = hasError,
        isRequired = isRequired,
        isEnabled = isEnabled,
        maxLines = maxLines,
        minLines = minLines,
        aboveIcon = {
            Text(
                text = "${value.value.text.length}/$maxSymbols",
                style = Theme.typography.caption2,
                modifier = Modifier
                    .padding(top = 7.dp)
                    .padding(end = 15.dp)
                    .align(Alignment.TopEnd)
            )
            Icon(
                painter = painterResource(R.drawable.stretch),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.BottomEnd)
            )
        },
        validators = validators.plus(ValidatorPatterns.inRange(minSymbols, maxSymbols))
    )
}

/**
 * Комопнент Input с логикой многостраничного ввода без валидатора максимальных символов.
 * Наследуется от [ValidatorInput]
 *
 * @param value [TextFieldValue] Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param label [String] Метка, которая будет отображаться внутри контейнера текстового поля.
 * @param maxLines [Int] Максимальная высота, выраженная в максимальном количестве видимых линий. Это
 * обязательно что 1 <= [minLines] <= [maxLines].
 * @param minLines минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines].
 * @param hasError [Boolean] Указывает, является ли текущее значение текстового поля ошибочным. Если
 * установлено значение true, то текстовое поле будет окрашено цветом ошибки
 * @param modifier [Modifier] Который должен быть применен к этому текстовому полю
 * @param isEnabled [Boolean] Управляет включенным состоянием этого текстового поля. При значении
 * false этот компонент будет не реагирует на ввод данных пользователем, и оно будет выглядеть
 * визуально отключенным и недоступным для доступа к сервисам.
 * @param readOnly [Boolean] Управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param onValueChange Коллбэк, когда служба ввода обновляет значения в текстовом поле [value]
 * @param isRequired [Boolean] Является данное ли поле обязательным
 * @param prefix [Composable] Необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix [Composable] Необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param visualTransformation [VisualTransformation] Преобразует визуальное представление входных данных [value]
 * @param keyboardOptions [KeyboardOptions] Определяет параметры программной клавиатуры
 * @param keyboardActions [KeyboardActions] коллбэки событий. Эти действия могут отличаться от того,
 * что вы указано в [KeyboardOptions.imeAction]
 * @param interactionSource [MutableInteractionSource], представляет поток [Interaction] для этого
 * текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param colors [TextFieldColors] Цветовое оформление в разных состояниях
 * @param validators [Validator] Параметры валидации, есть готовые в [ValidatorPatterns]
 *
 * @sample InputSamples.MultilineWithoutValidSymbolSample
 */
@Composable
fun MultiLineInput(
    value: MutableState<TextFieldValue>,
    label: String,
    maxLines: Int,
    minLines: Int,
    modifier: Modifier = Modifier,
    hasError: MutableState<Boolean> = remember { mutableStateOf(false) },
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    isRequired: Boolean = false,
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.colors(),
    validators: List<Validator> = emptyList(),
) {
    ValidatorInput(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        singleLine = false,
        prefix = prefix,
        suffix = suffix,
        label = label,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        colors = colors,
        hasError = hasError,
        isRequired = isRequired,
        isEnabled = isEnabled,
        maxLines = maxLines,
        minLines = minLines,
        aboveIcon = {
            Icon(
                painter = painterResource(R.drawable.stretch),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.BottomEnd)
            )
        },
        validators = validators
    )
}
