package com.cryptoemergency.cryptoemergency.ui.common.inputs

import android.util.Log
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.lib.validation.Validator
import com.cryptoemergency.cryptoemergency.lib.validation.ValidatorPatterns
import com.cryptoemergency.cryptoemergency.lib.validation.validation
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Компонент Input с логикой валидации. Наследуется от Input
 *
 * @param value Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param modifier Модификатор который должен быть применен к этому текстовому полю.
 * @param isEnabled Управляет включенным состоянием этого текстового поля. При значении "false" этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param readOnly управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param label Необязательная метка, которая будет отображаться внутри контейнера текстового поля.
 * @param postLabel метка отображаемая в правом вверхнем углу, или же напротив label
 * @param placeholder Необязательный заполнитель, который отображается, когда текстовое поле находится в фокусе, а
 * вводимый текст пуст
 * @param leadingIcon Необязательный начальный значок, который будет отображаться в начале текстового поля
 * контейнер
 * @param trailingIcon Необязательный завершающий значок, который будет отображаться в конце текстового поля
 * контейнер
 * @param prefix Необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix Необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param hasError Указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * @param visualTransformation Преобразует визуальное представление входных данных [value]
 * @param keyboardOptions Определяет параметры программной клавиатуры
 * @param keyboardActions Когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param singleLine При значении "true" это текстовое поле становится одним текстовым полем с горизонтальной прокруткой
 * вместо того, чтобы растягиваться на несколько строк. Клавиатуре будет сообщено, что клавиша возврата не отображается
 * в качестве [ImeAction]. Обратите внимание, что параметр [maxLines] будет проигнорирован, так как атрибуту
 * maxLines будет автоматически присвоено значение 1.
 * @param maxLines Максимальная высота, выраженная в максимальном количестве видимых линий. Это обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param minLines Минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param interactionSource указывает [MutableInteractionSource], представляющий поток [Interaction] с
 * для этого текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param contentAlignment Отношение позиционирования [aboveIcon] к [Input]
 * @param validators Параметры валидации, есть готовые в [ValidatorPatterns]
 */
@Composable
fun ValidatorInput(
    value: MutableState<TextFieldValue>,
    label: String,
    validators: List<Validator>,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    isEnabled: Boolean = true,
    isRequired: Boolean = false,
    readOnly: Boolean = false,
    aboveIcon: @Composable () -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    postLabel: @Composable (TextStyle) -> Unit = {},
    hasError: MutableState<Boolean> = mutableStateOf(false),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.colors(),
    contentAlignment: Alignment = Alignment.TopStart,
) {
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val successMessage = remember { mutableStateOf<String?>(null) }

    Column {
        Input(
            modifier = modifier,
            onValueChange = {
                onValueChange(it)

                validate(
                    text = it.text,
                    errorMessage = errorMessage,
                    successMessage = successMessage,
                    hasError = hasError,
                    validators,
                )
            },
            contentAlignment = contentAlignment,
            aboveIcon = aboveIcon,
            value = value,
            readOnly = readOnly,
            label = label,
            postLabel = postLabel,
            isError = hasError.value,
            isRequired = isRequired,
            prefix = prefix,
            suffix = suffix,
            isEnabled = isEnabled,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            colors = colors,
        )

        Column {
            Spacer(Modifier.height(4.dp))

            errorMessage.value?.let {
                Text(
                    text = it,
                    color = Theme.colors.error,
                    style = Theme.typography.caption2,
                )
            }

            if (!successMessage.value.isNullOrEmpty()) {
                Text(
                    text = successMessage.value!!,
                    color = Theme.colors.success,
                    style = Theme.typography.caption2,
                )
            }
        }
    }
}

/**
 * Внутренний метод валидации параметров текстового поля
 * */
private fun validate(
    text: String,
    errorMessage: MutableState<String?>,
    successMessage: MutableState<String?>,
    hasError: MutableState<Boolean>,
    validators: List<Validator>,
) {
    validation(text, validators) { hasErr, errMsg ->
        Log.d("hasErr", hasErr.toString())
        if (hasErr) {
            errorMessage.value = errMsg
            successMessage.value = null
            hasError.value = true
        } else {
            errorMessage.value = null
            successMessage.value = "Успешно!"
            hasError.value = false
        }
    }
}
