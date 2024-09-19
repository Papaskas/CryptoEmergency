package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.bottomBar.BottomBar
import com.cryptoemergency.cryptoemergency.ui.common.topBar.MainTopBar
import kotlin.math.roundToInt

/**
 * Компонент скрытие topBar и bottomBar при скроле вниз
 *
 * 1) TopBar & BottomBar не имеют [padding], что значит элементы под них проваливаются
 * 2) Когда начинается движение, они с помощью [offset] уходят за края
 * 3) Поскольку динамическое изменение [padding] тормознутое, то отстут от верха утановлен с помощью [offset]
 * и переменной [topOffset] заданный в [Surface]
 * 4) То есть дочерний элемент с помощью [offset] отступает только от верха, а когда происходит прокрутка
 * двигается вверх до ограничителя - высотой StatusBar
 * */
@Composable
fun FullScreen(
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val offsetHeight = 120.dp
    val heightPx = with(density) { offsetHeight.roundToPx().toFloat() }
    var offsetHeightPx by remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = offsetHeightPx + delta
                offsetHeightPx = newOffset.coerceIn(-heightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val topOffset by remember {
        derivedStateOf {
            (offsetHeight + offsetHeightPx.dp).coerceAtLeast(40.dp )
        }
    }

    Screen(
        topBar = {
            MainTopBar(
                Modifier.offset { IntOffset(x = 0, y = offsetHeightPx.roundToInt()) },
            )
        },
        bottomBar = {
            BottomBar(
                Modifier.offset { IntOffset(x = 0, y = -(offsetHeightPx.roundToInt())) },
            )
        },
        scaffoldModifier = Modifier.nestedScroll(nestedScrollConnection),
        horizontalPadding = 0.dp,
    ) {
        Surface(
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = topOffset.roundToPx()
                )
            },
            color = Theme.colors.surface1,
            shape = RoundedCornerShape(
                topStart = Theme.dimens.shape,
                topEnd = Theme.dimens.shape,
            ),
        ) {
           content()
        }
    }
}
