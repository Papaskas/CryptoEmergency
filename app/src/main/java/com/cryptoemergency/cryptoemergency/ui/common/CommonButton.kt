package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.modifiers.linearGradientBackground
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.providers.theme.currentTheme
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme

/**
 * @param modifier Модификатор, который будет применен к кнопке.
 * @param text Текст, который будет отображаться на кнопке.
 * @param onClick Обратный вызов, который должен быть вызван при нажатии кнопки.
 * @param buttonType Акцентирование кнопки
 * @param layoutWidth Поведение кнопки в родители. По умолчанию используется значение [LayoutWidth.MatchParent].
 * @param isEnabled Параметр определяющий включена кнопка или нет. По умолчанию используется значение true.
 */
@Composable
fun CommonButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Primary,
    layoutWidth: LayoutWidth = LayoutWidth.MatchParent,
    isEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Для видимости градиента
        ),
        modifier = modifier
            .then(
                when (buttonType) {
                    ButtonType.Primary -> {
                        modifier
                            .buttonStyle(layoutWidth)
                            .primaryStyle()
                    }
                    ButtonType.Secondary -> {
                        modifier
                            .buttonStyle(layoutWidth)
                            .secondaryStyle()
                    }
                }
            ),
        enabled = isEnabled,
    ) {
        Text(
            text = text,
            color = getTextColor(buttonType),
            textAlign = TextAlign.Center,
            style = getTextStyle(layoutWidth),
        )
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

enum class LayoutWidth {
    MatchParent, WrapContent
}

enum class ButtonType {
    Primary, Secondary
}
