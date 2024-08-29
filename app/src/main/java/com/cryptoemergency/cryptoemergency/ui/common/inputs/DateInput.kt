package com.cryptoemergency.cryptoemergency.ui.common.inputs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.lib.Convert
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton

/**
 * Комопнент Input с логикой Date. Наследуется от Input
 *
 * @param value Значение текста, которое будет отображаться в текстовом поле
 * @param modifier [Modifier], который должен быть применен к этому текстовому полю.
 * @param enabled управляет включенным состоянием этого текстового поля. При значении "false" этот компонент будет
 * не реагирует на ввод данных пользователем, и оно будет выглядеть визуально отключенным и недоступным
 * для доступа к сервисам.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    value: MutableState<TextFieldValue>,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val datePickerState = rememberDatePickerState()
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = datePickerState.selectedDateMillis?.let {
        Convert.millisToDate(it)
    } ?: ""

    LaunchedEffect(selectedDate) {
        value.value = TextFieldValue(selectedDate)
    }

    Box {
        Input(
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showDatePicker.value = !showDatePicker.value
                            }
                        }
                    }
                },
            readOnly = true,
            value = value,
            label = label,
            enabled = enabled,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                    Icon(
                        painter = painterResource(R.drawable.calendar__filled),
                        contentDescription = label,
                        tint = Theme.colors.text4
                    )
                }
            },
        )

        if (showDatePicker.value) {
            DatePickerModal(
                datePickerState,
                showDatePicker,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerModal(
    state: DatePickerState,
    showDatePicker: MutableState<Boolean>,
) {
    DatePickerDialog(
        onDismissRequest = {
            showDatePicker.value = !showDatePicker.value
        },
        confirmButton = {
            TextButton(onClick = {
                state.selectedDateMillis
                showDatePicker.value = !showDatePicker.value
            }) {
                Text("")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                showDatePicker.value = !showDatePicker.value
            }) {
                Text("Отмена")
            }
        },
        colors = DatePickerDefaults.colors().copy(
            containerColor = Theme.colors.surface1,
        ),
    ) {
        DatePicker(
            showModeToggle = false,
            state = state,
            colors = DatePickerDefaults.colors().copy(
                titleContentColor = Theme.colors.text1,
                headlineContentColor = Theme.colors.text1,
                weekdayContentColor = Theme.colors.text1,
                subheadContentColor = Theme.colors.text1,
                selectedDayContentColor = Theme.colors.text1,
                selectedDayContainerColor = Theme.colors.accent,
                todayContentColor = Theme.colors.text1,
                todayDateBorderColor = Theme.colors.text1,
                dayContentColor = Theme.colors.text1,
                yearContentColor = Theme.colors.text1,
                navigationContentColor = Theme.colors.text1,

                containerColor = Theme.colors.surface2,
            )
        )
    }
}
