package com.cryptoemergency.cryptoemergency.ui.common.buttons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.modifiers.linearGradientBackground
import com.cryptoemergency.cryptoemergency.providers.theme.provides.CompositionLocals.LocalTheme
import com.cryptoemergency.cryptoemergency.providers.theme.provides.Theme
import com.papaska.core.entity.local.ThemeEntity
import kotlinx.coroutines.launch

/**
 * @param modifier Модификатор, который будет применен к кнопке. Не следует применять [Modifier.padding]
 * @param text Текст, который будет отображаться на кнопке.
 * @param onClick Обратный вызов, который должен быть вызван при нажатии кнопки.
 * @param buttonType [ButtonType] Внешний вид кнопки
 * @param layoutWidth [LayoutWidth] Поведение кнопки в родители
 * @param isEnabled [Boolean] Параметр определяющий включена кнопка или нет
 * @param isLoading [Boolean] Анимация ожидания. Также блокирует эту кнопку
 */
@Composable
fun CommonButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Primary,
    layoutWidth: LayoutWidth = LayoutWidth.MatchParent,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Для видимости градиента
            disabledContainerColor = Color.Transparent,
        ),
        modifier = modifier
            .buttonStyle(layoutWidth)
            .then(
                when (buttonType) {
                    ButtonType.Primary -> {
                        modifier.primaryStyle(isEnabled)
                    }
                    ButtonType.Secondary -> {
                        modifier.secondaryStyle(isEnabled)
                    }
                }
            ),
        enabled = isEnabled && !isLoading,
    ) {
        Box {
            LoadingAnimate(isLoading, buttonType)

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
            when (LocalTheme.current) {
                ThemeEntity.DARK -> Color.White
                ThemeEntity.LIGHT -> Theme.colors.text6
                ThemeEntity.NULL -> Theme.colors.text6
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
private fun Modifier.primaryStyle(isEnabled: Boolean): Modifier = this.then(
    Modifier.linearGradientBackground(
        colors = listOf(
            Theme.colors.logoPink,
            Theme.colors.logoBlue,
        ),
        angleInDegrees = 100.0f,
        shape = CircleShape,
    )
    .then(
        if(isEnabled) {
            Modifier
        } else {
            Modifier.background(
                Color.DarkGray.copy(.5f),
                CircleShape,
            )
        }
    )
)

@Composable
private fun Modifier.secondaryStyle(isEnabled: Boolean): Modifier = this.then(
    if (isEnabled) {
        Modifier.background(
            color = Theme.colors.background4,
            shape = CircleShape,
        )
    } else {
        Modifier.background(
            color = Color.Gray,
            shape = CircleShape,
        )
    }
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
    isLoading: Boolean,
    buttonType: ButtonType,
) {
    val colors = when (buttonType) {
        ButtonType.Primary -> {
            listOf(
                Theme.colors.logoPink,
                Theme.colors.logoBlue,
            )
        }
        ButtonType.Secondary -> {
            listOf(
                Theme.colors.text5,
                Theme.colors.background4,
            )
        }
    }
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
