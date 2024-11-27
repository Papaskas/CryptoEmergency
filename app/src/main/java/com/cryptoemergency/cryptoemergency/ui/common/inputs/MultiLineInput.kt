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
import com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput.ValidatorInput

/**
 * Комопнент Input с логикой многостраничного ввода. Наследуется от ValidateInput со встроенным
 * паттерном максимальных и минимальных символов
 *
 * @param value значение вводимого текста, которое будет отображаться в текстовом поле
 * @param label метка, которая будет отображаться внутри контейнера текстового поля.
 * @param modifier [Modifier], который должен быть применен к этому текстовому полю.
 * @param isEnabled управляет включенным состоянием этого текстового поля. При значении false этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param readOnly управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param isError указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 * @param onValueChange Функция меняющая состояние вводимого текста
 * @param prefix необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param visualTransformation преобразует визуальное представление входных данных [value]
 * Например, вы можете использовать
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.Преобразование пароля] в
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 * @param keyboardOptions определяет параметры программной клавиатуры, которые содержат такие настройки
 * @param keyboardActions когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param maxLines максимальная высота, выраженная в максимальном количестве видимых линий. Это обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param minLines минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param interactionSource указывает [MutableInteractionSource], представляющий поток [Interaction] с
 * для этого текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param colors Палитра цветов для Input
 * @param validators Параметры валидации, есть готовые в [ValidatorPatterns]
 * @param maxSymbols Максимальное количество символов
 * @param isRequired является ли поле обязательным
 */
@Composable
fun MultiLineInput(
    value: MutableState<TextFieldValue>,
    label: String,
    maxLines: Int,
    minLines: Int,
    minSymbol: Int,
    maxSymbols: Int,
    isError: MutableState<Boolean>,
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
    val validatorsList = validators.toMutableList()
    validatorsList.add(ValidatorPatterns.inRange(minSymbol, maxSymbols))

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
        hasError = isError,
        isRequired = isRequired,
        isEnabled = isEnabled,
        maxLines = maxLines,
        minLines = minLines,
        postLabel = {
            Text(
                text = "${value.value.text.length}/$maxSymbols",
                style = it,
            )
        },
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
