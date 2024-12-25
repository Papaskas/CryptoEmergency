package com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.lib.validation.Validator
import com.cryptoemergency.cryptoemergency.lib.validation.ValidatorPatterns
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input
import com.cryptoemergency.cryptoemergency.ui.common.inputs.InputSamples

/**
 * Компонент Input с логикой валидации. Наследуется от Input
 *
 * @param value [TextFieldValue] Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param label [String] Метка, которая будет отображаться внутри контейнера текстового поля
 * @param validators [Validator] Список валидаторов, используете готовые [ValidatorPatterns]
 * @param modifier [Modifier] Модификатор применяемый к к разметке текстового поля
 * @param showValidatorMessage [Boolean] Показывать ли сообщения об ошибках
 * @param successMessage [StringRes] Необязательное сообщение об успешной валидации
 * @param onValueChange Коллбэк, когда служба ввода обновляет значения в текстовом поле [value]
 * @param isEnabled [Boolean] Управляет включенным состоянием этого текстового поля. При значении "false" этот
 * компонент не будет реагировать на ввод данных пользователем, и оно будет выглядеть визуально отключенным
 * и недоступным
 * @param isRequired [Boolean] Добавляет значок обязательного текстового поля. Обратите внимание что
 * это определяет только визуальный стиль, программно это никак не запрещает не вводить значение
 * @param readOnly [Boolean] Управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него
 * @param aboveIcon [Composable] Метка с абсолютным позиционированием над текстовым полем. Позицию
 * задавать с помощью [Alignment] на самого себя
 * @param leadingIcon [Composable] Необязательный начальный значок, который будет отображаться в начале
 * текстового поля
 * @param trailingIcon [Composable] Необязательный завершающий значок, который будет отображаться
 * в конце текстового поля
 * @param prefix [Composable] Необязательный префикс, который будет отображаться перед вводимым текстом
 * в текстовом поле
 * @param suffix [Composable] Необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param hasError [Boolean] Указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, то текстовое поле будет окрашено цветом ошибки
 * @param visualTransformation [VisualTransformation] Преобразует визуальное представление входных данных [value]
 * @param keyboardOptions [KeyboardOptions] Определяет параметры программной клавиатуры
 * @param keyboardActions [KeyboardActions] коллбэки событий. Эти действия могут отличаться от того,
 * что вы указано в [KeyboardOptions.imeAction]
 * @param singleLine [Boolean] При значении "true" это текстовое поле становится одним текстовым полем
 * с горизонтальной прокруткой вместо того, чтобы растягиваться на несколько строк. Клавиатуре будет сообщено,
 * что клавиша возврата не отображается в качестве [ImeAction]. Обратите внимание, что параметр [maxLines]
 * будет проигнорирован, так как атрибуту [maxLines] будет автоматически присвоено значение 1.
 * @param maxLines [Int] Максимальная высота, выраженная в максимальном количестве видимых линий. Обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param minLines [Int] Минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 * чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param interactionSource [MutableInteractionSource], представляет поток [Interaction] для этого
 * текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 * @param colors [TextFieldColors] Цветовое оформление в разных состояниях
 *
 * @sample InputSamples.ValidatorInputSample
 */
@Composable
fun ValidatorInput(
    value: MutableState<TextFieldValue>,
    label: String,
    validators: List<Validator>,
    modifier: Modifier = Modifier,
    showValidatorMessage: Boolean = true,
    @StringRes successMessage: Int? = null,
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    isEnabled: Boolean = true,
    isRequired: Boolean = false,
    readOnly: Boolean = false,
    aboveIcon: @Composable BoxScope.() -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    hasError: MutableState<Boolean> = mutableStateOf(false),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val vm: ValidatorInputViewModel = hiltViewModel()

    Column {
        Input(
            modifier = modifier,
            onValueChange = {
                onValueChange(it)

                vm.validate(
                    it.text,
                    validators,
                    hasError,
                    successMessage,
                )
            },
            aboveIcon = aboveIcon,
            value = value,
            readOnly = readOnly,
            label = label,
            hasError = hasError.value,
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

        ValidatorMessageBlock(vm, showValidatorMessage)
    }
}

@Composable
private fun ValidatorMessageBlock(
    vm: ValidatorInputViewModel,
    showValidatorMessage: Boolean,
) {
    if (!showValidatorMessage) return

    Column {
        Spacer(Modifier.height(4.dp))

        vm.errorMessage.value?.let {
            Text(
                text = it,
                color = Theme.colors.error,
                style = Theme.typography.caption2,
            )
        }

        vm.successMessage.value?.let {
            Text(
                text = it,
                color = Theme.colors.success,
                style = Theme.typography.caption2,
            )
        }
    }
}
