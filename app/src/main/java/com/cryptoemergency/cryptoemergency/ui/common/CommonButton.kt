package com.cryptoemergency.cryptoemergency.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonColors
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.modifiers.linearGradientBackground
import com.cryptoemergency.cryptoemergency.providers.theme.Theme

/**
 * @param modifier модификатор, который будет применен к кнопке.
 * @param text Текст, который будет отображаться на кнопке.
 * @param onClick обратный вызов, который должен быть вызван при нажатии кнопки.
 * @param layout Поведение кнопки в родители. По умолчанию используется значение [LayoutWidth.MatchParent].
 * @param isEnabled Параметр определяющий включена кнопка или нет. По умолчанию используется значение true.
 */
@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    layout: LayoutWidth = LayoutWidth.MatchParent,
    isEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent // Для видимости градиента
        ),
        modifier = modifier.buttonStyle(layout),
        enabled = isEnabled,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = getTextStyle(layout),
        )
    }
}

@Composable
private fun Modifier.buttonStyle(layout: LayoutWidth): Modifier = this.then(
    when (layout) {
        LayoutWidth.MatchParent -> {
            Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 50.dp)
                .linearGradientBackground(
                    colors = listOf(
                        Color(0xFFF103CF),
                        Color(0xFF0059F7),
                    ),
                    angleInDegrees = 100.0f,
                    shape = CircleShape,
                )
        }
        LayoutWidth.WrapContent -> {
            Modifier
                .wrapContentWidth()
                .sizeIn(minHeight = 36.dp)
                .linearGradientBackground(
                    colors = listOf(
                        Color(0xFFF103CF),
                        Color(0xFF0059F7),
                    ),
                    angleInDegrees = 100.0f,
                    shape = CircleShape,
                )
        }
    }
)

@Composable
private fun getTextStyle(
    layout: LayoutWidth,
): TextStyle {
    return when (layout) {
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
