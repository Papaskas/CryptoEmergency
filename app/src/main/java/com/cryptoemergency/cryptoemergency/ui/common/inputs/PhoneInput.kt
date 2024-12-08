package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.validation.Validator
import com.cryptoemergency.cryptoemergency.lib.validation.ValidatorPatterns
import com.cryptoemergency.cryptoemergency.ui.common.inputs.maskInput.MaskInput
import com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput.ValidatorInput

/**
 * Комопнент [Input] с логикой ввода номера телефона с помощью маски. Наследуется от [MaskInput]
 *
 * @param value [TextFieldValue] Значение вводимого текста, которое будет отображаться в текстовом поле
 * @param modifier [Modifier] Модификатор применяемый к к разметке текстового поля
 * @param mask [String] Маска ввода. Примеры: xxx xxx xx xx, (xxx) xxx xx xx, +7-xxx-xxx-xx-xx
 * @param maskChar [Char] Символ который считается частью маски.
 * @param onValueChange Коллбэк, когда служба ввода обновляет значения в текстовом поле [value]
 * @param label [String] Метка, которая будет отображаться внутри контейнера текстового поля
 * @param hasError [Boolean] Указывает, является ли текущее значение текстового поля ошибочным.
 * Если установлено значение true, то текстовое поле будет окрашено цветом ошибки
 * @param isEnabled [Boolean] Управляет включенным состоянием этого текстового поля. При значении "false" этот
 * компонент не будет реагировать на ввод данных пользователем, и оно будет выглядеть визуально отключенным
 * и недоступным
 * @param isRequired [Boolean] Добавляет значок обязательного текстового поля. Обратите внимание что
 * это определяет только визуальный стиль, программно это никак не запрещает не вводить значение
 * @param readOnly [Boolean] Управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него
 * @param keyboardOptions [KeyboardOptions] Определяет параметры программной клавиатуры
 * @param keyboardActions [KeyboardActions] коллбэки событий. Эти действия могут отличаться от того,
 * что вы указано в [KeyboardOptions.imeAction]
 * @param interactionSource [MutableInteractionSource], представляет поток [Interaction] для этого
 * текстового поля. Вы можете создать и передать свой собственный "запоминаемый" экземпляр для наблюдения
 * [Interaction] и настраивать внешний вид / поведение этого текстового поля в различных состояниях.
 *
 * @see <a href="https://stackoverflow.com/questions/71274129/phone-number-visual-transformation-in-jetpack-compose">Исходный код</a>
 *
 * @sample InputSamples.PhoneInputSample
 */
@Composable
fun PhoneInput(
    value: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    mask: String = "+7 (xxx) xxx-xx-xx",
    maskChar: Char = 'x',
    onValueChange: (TextFieldValue) -> Unit = { value.value = it },
    label: String = LocalContext.current.getString(R.string.phone_number),
    hasError: MutableState<Boolean> = mutableStateOf(false),
    isEnabled: Boolean = true,
    isRequired: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    validators: List<Validator> = listOf(ValidatorPatterns.onlyNumber),
) {
    MaskInput(
        value = value,
        modifier = modifier,
        mask = mask,
        maskChar = maskChar,
        onValueChange = onValueChange,
        label = label,
        hasError = hasError,
        isEnabled = isEnabled,
        isRequired = isRequired,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Phone
        ),
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        validators = validators,
    )
}
