package com.cryptoemergency.cryptoemergency.modifiers

import android.graphics.Shader
import androidx.compose.foundation.background
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Modifier.linearGradientBackground(
    colors: List<Color>,
    stops: List<Float>? = null,
    tileMode: TileMode = TileMode.Clamp,
    angleInDegrees: Float = 0f,
    useAsCssAngle: Boolean = false,
    shape: Shape = RectangleShape,
): Modifier {
    return this.background(
        brush = LinearGradient(
            colors,
            stops,
            tileMode,
            angleInDegrees,
            useAsCssAngle,
        ),
        shape = shape,
    )
}

/**
 * Создает линейный градиент с заданными цветами и углом наклона.
 *
 * @param colors цвета градиента
 * @param stops смещения, чтобы определить, как распределяются цвета по всему градиенту
 * вертикальный градиент
 * @param tileMode Определяет поведение шейдера при заполнении области за пределами
 * ее границ. По умолчанию используется [TileMode.Clamp] для повторения краевых пикселей
 * @param angleInDegrees определяет угол градиента в градусах
 * @param useAsCssAngle Определяет, следует ли использовать угол градиента CSS
 * по умолчанию используется декартов угол
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/gradient/linear-gradient">
 * линейно-градиентный</a>
 */
@Immutable
private class LinearGradient(
    private val colors: List<Color>,
    private val stops: List<Float>? = null,
    private val tileMode: TileMode = TileMode.Clamp,
    angleInDegrees: Float = 0f,
    useAsCssAngle: Boolean = false,
) : ShaderBrush() {

    // handle edge cases like: -1235, ...
    private val normalizedAngle: Float = if (useAsCssAngle) {
        ((90 - angleInDegrees) % 360 + 360) % 360
    } else {
        (angleInDegrees % 360 + 360) % 360
    }
    private val angleInRadians: Float = Math.toRadians(normalizedAngle.toDouble()).toFloat()

    override fun createShader(size: Size): Shader {
        val (from, to) = getGradientCoordinates(size = size)

        return LinearGradientShader(
            colors = colors,
            colorStops = stops,
            from = from,
            to = to,
            tileMode = tileMode
        )
    }

    private fun getGradientCoordinates(size: Size): Pair<Offset, Offset> {
        val diagonal = sqrt(size.width.pow(2) + size.height.pow(2))
        val angleBetweenDiagonalAndWidth = acos(size.width / diagonal)
        val angleBetweenDiagonalAndGradientLine =
            if (
                (normalizedAngle > 90 && normalizedAngle < 180)
                ||
                (normalizedAngle > 270 && normalizedAngle < 360)
            ) {
                PI.toFloat() - angleInRadians - angleBetweenDiagonalAndWidth
            } else {
                angleInRadians - angleBetweenDiagonalAndWidth
            }
        val halfGradientLine = abs(cos(angleBetweenDiagonalAndGradientLine) * diagonal) / 2

        val horizontalOffset = halfGradientLine * cos(angleInRadians)
        val verticalOffset = halfGradientLine * sin(angleInRadians)

        val start = size.center + Offset(-horizontalOffset, verticalOffset)
        val end = size.center + Offset(horizontalOffset, -verticalOffset)

        return start to end
    }
}
