package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    showBottomSheet: MutableState<Boolean>,
    title: String,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    contentPadding: Dp = Theme.values.padding,
    actionIcon: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (!showBottomSheet.value) return

    ModalBottomSheet(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(
            topStart = Theme.shapes.common,
            topEnd = Theme.shapes.common,
        ),
        sheetState = sheetState,
        dragHandle = null,
        scrimColor = Color.Black.copy(alpha = .9f),
        onDismissRequest = { showBottomSheet.value = false },
        containerColor = Theme.colors.background2,
    ) {
        Header(title, sheetState, showBottomSheet, actionIcon)

        CommonHorizontalDivider()

        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(
    title: String,
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>,
    actionIcon: @Composable (() -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.padding(Theme.values.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = Theme.typography.h2,
            color = Theme.colors.text1,
        )

        Spacer(Modifier.weight(1f))

        if (actionIcon != null) {
            actionIcon()
        } else {
            IconButton(onClick = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    showBottomSheet.value = false
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "Закрыть модальное окно",
                )
            }
        }
    }
}
