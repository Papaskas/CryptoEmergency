package com.cryptoemergency.cryptoemergency.ui.common.buttons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.modifiers.linearGradientBackground
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.providers.theme.currentTheme
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme
import kotlinx.coroutines.launch

/**
 * @param modifier Модификатор, который будет применен к кнопке.
 * @param text Текст, который будет отображаться на кнопке.
 * @param onClick Обратный вызов, который должен быть вызван при нажатии кнопки.
 * @param buttonType Акцентирование кнопки
 * @param layoutWidth Поведение кнопки в родители
 * @param isEnabled Параметр определяющий включена кнопка или нет
 * @param isLoading Анимация ожидания. Также блокирует эту кнопку
 */
@Composable
fun CommonButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Primary,
    layoutWidth: LayoutWidth = LayoutWidth.MatchParent,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Для видимости градиента
        ),
        modifier = modifier
            .buttonStyle(layoutWidth)
            .then(
                when (buttonType) {
                    ButtonType.Primary -> {
                        modifier.primaryStyle()
                    }
                    ButtonType.Secondary -> {
                        modifier.secondaryStyle()
                    }
                }
            ),
        enabled = isEnabled && !isLoading,
    ) {
        Box {
            LoadingAnimate(isLoading)

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
                text = text,
                color = getTextColor(buttonType),
                textAlign = TextAlign.Center,
                style = getTextStyle(layoutWidth),
            )
        }
    }
}

@Composable
private fun getTextColor(
    buttonType: ButtonType
): Color {
    return when (buttonType) {
        ButtonType.Primary -> {
            Color.White
        }
        ButtonType.Secondary -> {
            when (currentTheme) {
                CurrentTheme.DARK -> {
                    Color.White
                }
                CurrentTheme.LIGHT -> {
                    Theme.colors.text6
                }
                CurrentTheme.NULL -> {
                    Theme.colors.text6
                }
            }
        }
    }
}

@Composable
private fun Modifier.buttonStyle(layoutWidth: LayoutWidth): Modifier = this.then(
    when (layoutWidth) {
        LayoutWidth.MatchParent -> {
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        }
        LayoutWidth.WrapContent -> {
            Modifier
                .wrapContentWidth()
                .height(50.dp)
        }
    }
)

@Composable
private fun Modifier.primaryStyle(): Modifier = this.then(
    Modifier.linearGradientBackground(
        colors = listOf(
            Color(0xFFF103CF),
            Color(0xFF0059F7),
        ),
        angleInDegrees = 100.0f,
        shape = CircleShape,
    )
)

@Composable
private fun Modifier.secondaryStyle(): Modifier = this.then(
    Modifier.background(
        color = Theme.colors.background4,
        shape = CircleShape,
    )
)

@Composable
private fun getTextStyle(
    layoutWidth: LayoutWidth,
): TextStyle {
    return when (layoutWidth) {
        LayoutWidth.MatchParent -> {
            Theme.typography.body1
        }
        LayoutWidth.WrapContent -> {
            Theme.typography.caption1
        }
    }
}

@Composable
private fun LoadingAnimate(
    isLoading: Boolean
) {
    val colors = listOf(
        Theme.colors.logoPink,
        Theme.colors.logoBlue,
    )
    val angle = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {
            angle.animateTo(
                targetValue = 180f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    if (isLoading) {
        Box(Modifier
            .fillMaxSize()
            .linearGradientBackground(
                colors = colors,
                angleInDegrees = angle.value,
                shape = CircleShape,
            )
        )
    }
}

enum class LayoutWidth {
    MatchParent, WrapContent
}

enum class ButtonType {
    Primary, Secondary
}
