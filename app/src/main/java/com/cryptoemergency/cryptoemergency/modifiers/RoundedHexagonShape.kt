package com.cryptoemergency.cryptoemergency.modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.cryptoemergency.cryptoemergency.R

/**
 * Модификатор обрезки контента под hexagon со скругленными краями
 *
 * @param strokeColor Цвет обводки, если null, то нету
 * @param strokeWidth Толщина линии обводки
 *
 * @sample PreviewSample
 **/
@Composable
fun Modifier.roundedHexagonShape(
    strokeColor: Color? = null,
    strokeWidth: Float = 2f,
): Modifier {
    return this.drawWithCache {
        val roundedPolygon = RoundedPolygon(
            numVertices = 6,
            radius = size.minDimension / 2,
            centerX = size.width / 2,
            centerY = size.height / 2,
            rounding = CornerRounding(
                radius = size.minDimension / 10f,
                smoothing = 0.1f
            )
        )

        val path = roundedPolygon.toPath().asComposePath()

        onDrawBehind {
            rotate(degrees = 90f, pivot = Offset(size.width / 2, size.height / 2)) {
                drawPath(path, color = Color.Transparent)
                if(strokeColor != null) {
                    drawPath(path, color = strokeColor, style = Stroke(width = strokeWidth))
                }
            }
        }

        onDrawWithContent {
            rotate(90f) {
                if(strokeColor != null) {
                    drawPath(path, color = strokeColor, style = Stroke(width = strokeWidth))
                }

                clipPath(path) {
                    this@onDrawWithContent.drawContent()
                }
            }
        }
    }
        .rotate(270f) // Фикс
}

@Preview
@Composable
private fun PreviewSample() {
    Image(
        painter = painterResource(R.drawable.github__active),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .roundedHexagonShape(
                strokeColor = Color.Red,
                strokeWidth = 50f,
            )
            .fillMaxSize()
    )
}
