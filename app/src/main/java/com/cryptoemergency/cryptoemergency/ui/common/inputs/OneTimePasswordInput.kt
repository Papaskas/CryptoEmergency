package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * Компонент OTP
 *
 * @param value значение вводимого текста, которое будет отображаться в текстовом поле
 * @param isEnabled управляет включенным состоянием этого текстового поля. При значении false этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param readOnly управляет состоянием текстового поля, доступного для редактирования. При значении
 * "true" текстовое поле не может быть изменено. Однако пользователь может сфокусировать его и
 * скопировать текст из него. Текстовые поля, доступные только для чтения, обычно используются для
 * отображения предварительно заполненных форм, которые пользователь не может редактировать.
 * @param label метка, которая будет отображаться внутри контейнера текстового поля.
 * @param isError указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * @param visualTransformation преобразует визуальное представление входных данных [value]
 * Например, вы можете использовать
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.Преобразование пароля] в
 * создайте текстовое поле для ввода пароля. По умолчанию визуальное преобразование не применяется.
 * @param keyboardOptions определяет параметры программной клавиатуры, которые содержат такие настройки
 * @param keyboardActions когда служба ввода выполняет действие IME, вызывается соответствующий обратный вызов
 *. Обратите внимание, что это действие IME может отличаться от того, что вы указали в
 * [KeyboardOptions.imeAction].
 * @param isFocused Выделено ли поле
 */
@Composable
fun OneTimePasswordInput(
    value: MutableState<String>,
    label: String, // TODO: no label
    size: Int, // TODO: doc
    placeholder: String, // TODO: doc
    modifier: Modifier = Modifier,
    spacedBy: Dp = 10.dp, // TODO: doc
    icons: Map<@Composable () -> Unit, Int> = emptyMap(), // TODO: doc
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocused: Boolean = interactionSource.collectIsFocusedAsState().value,
) {
    var otp by remember { mutableStateOf(List(size) { "" }) }
    val isError = remember { mutableStateOf(false) }

    val focusRequesters = List(otp.size) { FocusRequester() }
    var focusedIndex by remember { mutableIntStateOf(-1) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
    ) {
        otp.forEachIndexed { index, _ ->
            OutlinedTextField(
                value = otp[index],
                onValueChange = { v ->
                    if (v.length <= 1) {
                        otp = otp.toMutableList().apply { set(index, v) }
                        if (otp.all { it.isNotEmpty() }) {
                            value.value = otp.joinToString("")
                        }
                    }
                },
                shape = RoundedCornerShape(Theme.dimens.radius),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .size(60.dp)
                    //.clip(RoundedCornerShape(Theme.dimens.radius))
                    .background(
                        color = Theme.colors.surface1,
                        shape = RoundedCornerShape(Theme.dimens.radius),
                    )
                    .focusRequester(focusRequesters[index])
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            focusedIndex = index
                        }
                    }
            )
        }
    }
}

@Composable
private fun OtpInput(
    value: MutableState<String>
) {

}

@Preview
@Composable
private fun Preview() {
    val value = remember { mutableStateOf("") }

    OneTimePasswordInput(
        value = value,
        label = "",
        size = 3,
        placeholder = "",
        spacedBy = 8.dp,
    )
}
