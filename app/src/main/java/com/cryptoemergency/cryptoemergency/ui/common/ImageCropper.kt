package com.cryptoemergency.cryptoemergency.ui.common

import android.graphics.Bitmap
import android.graphics.Rect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.canhub.cropper.CropImageView

/**
 * Компонент изображения с возможностью обрезки [github](https://github.com/CanHub/Android-Image-Cropper/tree/main)
 *
 * Обрезка происходит после того ка сетку отпустили
 *
 * @param bitmap Изображение для обрезки
 * @param modifier Накладывает [Modifier] на [AndroidView], некоторые стили могут не работать
 * @param onMoved Событие при передвижение сетки
 * @param onStop Событие после отпуска сетки. Идет после сохранения
 * @param settings Настройки для уникальных случаев. Смотри [CropImageView]
 * @param onResult Колбэк на получение результата
 *
 * @return [Bitmap] обрезанного изображения
 **/
@Composable
fun ImageCropper(
    bitmap: Bitmap,
    modifier: Modifier = Modifier,
    onMoved: (Rect?) -> Unit = {},
    onStop: (Rect?) -> Unit = {},
    settings: CropImageView.() -> Unit = {},
    onResult: (Bitmap?) -> Unit,
) {
    val state = remember { mutableStateOf<Bitmap?>(null) }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            CropImageView(ctx).apply {
                setImageBitmap(bitmap)
                setOnCropImageCompleteListener { _, result ->
                    if (result.isSuccessful) {
                        state.value = result.bitmap
                        onResult(result.bitmap)
                    }
                }
                setOnSetCropOverlayMovedListener { // Сетка перемещается
                    onMoved(it)
                }
                setOnSetCropOverlayReleasedListener {// После отпускания сетки
                    croppedImageAsync() // Save image
                    onStop(it)
                }
                settings()
            }
        },
    )
}
