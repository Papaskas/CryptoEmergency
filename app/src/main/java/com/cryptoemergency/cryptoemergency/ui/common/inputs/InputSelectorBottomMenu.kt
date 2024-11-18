package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.modifiers.commonBorder
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider

/**
 * Компонент Input с выбором элементов
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
 * @param expanded Состояние открытия меню
 */
@Composable
fun InputSelectorBottomMenu(
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    expanded: MutableState<Boolean> = remember { mutableStateOf(false) },
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val selectedOption = remember { mutableStateOf(items[defaultSelectItem]) }

    LaunchedEffect(selectedOption.value) {
        selectedItem.value = TextFieldValue(selectedOption.value)
    }

    Column(
        modifier = modifier,
    ) {
        Input(
            value = selectedItem,
            isFocused = false,
            readOnly = true,
            label = label,
            isError = isError.value,
            isRequired = isRequired,
            prefix = prefix,
            suffix = suffix,
            isEnabled = isEnabled,
            shape = inputShape(expanded.value),
            trailingIcon = {
                InputTrailingIcon(label, expanded)
            },
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = inputInteractionSource(
                interactionSource,
                expanded,
            ),
            singleLine = singleLine,
            colors = colors,
        )

        DropDown(
            expanded = expanded.value,
            selectedOption = selectedOption,
            items = items,
        )
    }
}

@Composable
private fun inputShape(
    expanded: Boolean,
): RoundedCornerShape {
    return if (expanded) {
        RoundedCornerShape(
            topStart = Theme.dimens.radius,
            topEnd = Theme.dimens.radius,
        )
    } else {
        RoundedCornerShape(Theme.dimens.radius)
    }
}

@Composable
private fun inputInteractionSource(
    interactionSource: MutableInteractionSource,
    expanded: MutableState<Boolean>,
): MutableInteractionSource {
    return interactionSource
        .also { interaction ->
            LaunchedEffect(interaction) {
                interaction.interactions.collect {
                    if (it is PressInteraction.Release) {
                        expanded.value = !expanded.value
                    }
                }
            }
        }
}

@Composable
private fun InputTrailingIcon(
    label: String,
    expanded: MutableState<Boolean>,
) {
    val rotation by animateFloatAsState(if (expanded.value) 180f else 0f, label = label)

    IconButton(onClick = {
        expanded.value = !expanded.value
    }) {
        Icon(
            painter = painterResource(R.drawable.arrow_up),
            contentDescription = label,
            tint = Theme.colors.text1,
            modifier = Modifier.rotate(rotation)
        )
    }
}

@Composable
private fun DropDown(
    expanded: Boolean,
    selectedOption: MutableState<String>,
    items: Array<String>,
) {
    val density = LocalDensity.current

    AnimatedVisibility(
        modifier = Modifier
            .commonBorder(
                shape = RoundedCornerShape(
                    bottomStart = Theme.dimens.radius,
                    bottomEnd = Theme.dimens.radius,
                ),
                isError = false,
                isFocused = false,
            )
            .background(
                color = Theme.colors.surface1,
                shape = RoundedCornerShape(
                    bottomStart = Theme.dimens.radius,
                    bottomEnd = Theme.dimens.radius,
                )
            )
            .zIndex(-1f),
        visible = expanded,
        enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Column {
            items.forEach { option ->
                DropdownMenuItem(
                    onClick = { selectedOption.value = option },
                    text = {
                        DropDownText(
                            option,
                            selectedOption.value
                        )
                    },
                    trailingIcon = {
                        DropDownIcon(
                            option,
                            selectedOption.value
                        )
                    },
                )
                if (items.last() != option) {
                    CommonHorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun DropDownText(
    option: String,
    selectedOption: String,
) {
    Text(
        text = option,
        style = Theme.typography.body1,
        color = if (selectedOption == option) {
            Theme.colors.accent
        } else {
            Theme.colors.text1
        }
    )
}

@Composable
private fun DropDownIcon(
    option: String,
    selectedOption: String,
) {
    if (selectedOption == option) {
        Icon(
            painter = painterResource(R.drawable.success),
            contentDescription = null,
            tint = Theme.colors.accent,
        )
    }
}
