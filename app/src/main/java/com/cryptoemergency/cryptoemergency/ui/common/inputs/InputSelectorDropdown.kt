package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.R

/**
 * Компонент Input с выбора элементов в виде выпадающего меню
 *
 * @param selectedItem Выбранный элемент
 * @param items Множество значений для выбора селектором
 * @param defaultSelectItem Выбранный элемент по умолчанию
 * @param modifier Модификатор который должен быть применен к этому текстовому полю.
 * @param isEnabled Управляет включенным состоянием этого текстового поля. При значении "false" этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 * @param label Необязательная метка, которая будет отображаться внутри контейнера текстового поля.
 * @param prefix Необязательный префикс, который будет отображаться перед вводимым текстом в текстовом поле
 * @param suffix Необязательный суффикс, который будет отображаться после вводимого текста в текстовом поле
 * @param isError Указывает, является ли текущее значение текстового поля ошибочным. Если установлено
 * значение true, меткаб нижний индикатор и завершающий значок по умолчанию будут отображаться цветом ошибки
 * @param singleLine При значении "true" это текстовое поле становится одним текстовым полем с горизонтальной прокруткой
 * вместо того, чтобы растягиваться на несколько строк. Клавиатуре будет сообщено, что клавиша возврата не отображается
 * в качестве [ImeAction]. Обратите внимание, что параметр [maxLines] будет проигнорирован, так как атрибуту
 * maxLines будет автоматически присвоено значение 1.
 * @param maxLines Максимальная высота, выраженная в максимальном количестве видимых линий. Это обязательно
 * что 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 * @param minLines Минимальная высота, выраженная в минимальном количестве видимых линий. Требуется
 *, чтобы 1 <= [minLines] <= [maxLines]. Этот параметр игнорируется, если значение [singleLine] равно true.
 */
@Composable
fun InputSelectorDropdown(
    label: String,
    selectedItem: MutableState<TextFieldValue>,
    items: Array<String>,
    modifier: Modifier = Modifier,
    defaultSelectItem: Int = 0,
    isEnabled: Boolean = true,
    isRequired: Boolean = false,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    isError: MutableState<Boolean> = mutableStateOf(false),
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("") }

    SideEffect {
        selectedItem.value = TextFieldValue(items[defaultSelectItem])
    }

    LaunchedEffect(selectedOption) {
        selectedItem.value = TextFieldValue(selectedOption.value)
    }

    Box {
        Input(
            modifier = modifier,
            value = selectedItem,
            readOnly = true,
            label = label,
            isError = isError.value,
            isRequired = isRequired,
            prefix = prefix,
            suffix = suffix,
            isEnabled = isEnabled,
            trailingIcon = {
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_up),
                        contentDescription = label,
                    )
                }
            },
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                expanded.value = !expanded.value
                            }
                        }
                    }
                },
            singleLine = singleLine,
            colors = colors,
        )

        DropDown(
            expanded = expanded,
            selectedOption = selectedOption,
            items = items,
        )
    }
}

@Composable
private fun DropDown(
    expanded: MutableState<Boolean>,
    selectedOption: MutableState<String>,
    items: Array<String>,
) {
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }
    ) {
        items.forEach { option ->
            DropdownMenuItem(
                onClick = { selectedOption.value = option },
                text = {
                    Text(
                        text = option,
                    )
                }
            )
        }
    }
}