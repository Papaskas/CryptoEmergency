package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    showBottomSheet: MutableState<Boolean>,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()

    if(showBottomSheet.value) {
        ModalBottomSheet(
            modifier = modifier.fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            sheetState = sheetState,
            //scrimColor = Color(0x50666666),
            dragHandle = null,
            onDismissRequest = { showBottomSheet.value = false },
            containerColor = Theme.colors.background2,
        ) {
            Column {
                Row(
                    Modifier.padding(Theme.values.padding),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = title,
                        style = Theme.typography.h2,
                        color = Theme.colors.text1,
                    )

                    Spacer(Modifier.weight(1f))

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

                CommonHorizontalDivider()

                Column(
                    Modifier.padding(Theme.values.padding)
                ) {
                    content()
                }
            }
        }
    }
}
