package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset

/**
 *
 * @param speed Скорость прокручивания текста
 *
 * */
@Composable
fun RunningLine(
    modifier: Modifier = Modifier,
    speed: Float = 50f,
    content: @Composable (modifier: Modifier) -> Unit,
) {
    var textWidth by remember { mutableFloatStateOf(0f) }
    var boxWidth by remember { mutableFloatStateOf(0f) }
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(textWidth, boxWidth) {
        if (textWidth > boxWidth) {
            offsetX.snapTo(boxWidth)
            offsetX.animateTo(
                targetValue = -textWidth,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = ((textWidth + boxWidth) / speed * 1000).toInt(),
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                boxWidth = coordinates.size.width.toFloat()
            }
    ) {
        content(
            Modifier
                .offset { IntOffset(offsetX.value.toInt(), 0) }
                .onGloballyPositioned { coordinates ->
                    textWidth = coordinates.size.width.toFloat()
                }
        )
    }
}
