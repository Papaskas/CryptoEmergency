package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.delay

/**
 * Компонент Input с логикой обработки валидации. Наследуется от Input
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
@Composable
fun ValidateInput(
    value: MutableState<TextFieldValue>,
    label: String,
    onValidate: () -> Unit,
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
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    errorMessage: String? = null,
    successMessage: String? = null,
) {
    val lastInteractionTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            if (System.currentTimeMillis() - lastInteractionTime > 1000 && value.value.text.isNotEmpty()) {
                onValidate()
            }
        }
    }

    Input(
        modifier = modifier.onFocusChanged { focusState ->
            if (!focusState.isFocused && value.value.text.isNotEmpty()) {
                onValidate()
            }
        },
        value = value,
        readOnly = readOnly,
        label = label,
        isError = isError,
//        prefix = prefix,
//        suffix = suffix,
//        supportingText = supportingText,
        enabled = enabled,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        // colors
    )
}
