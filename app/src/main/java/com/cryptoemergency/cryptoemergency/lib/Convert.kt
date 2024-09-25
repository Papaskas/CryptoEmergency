package com.cryptoemergency.cryptoemergency.lib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Convert {
    /**
     * Функция преобразовывающая UnixTime в дату по паттерну
     * */
    fun millisToDate(
        millis: Long,
        pattern: String = "MM/dd/yyyy"
    ): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(Date(millis))
    }

    /**
     * Функция преобразовывающая Uri
     *
     * @param format Формат данных - jpeg, png
     * @param quality Качество сжатия изображение
     * */
    fun uriToBase64(
        uri: Uri,
        context: Context,
        format: Bitmap.CompressFormat,
        quality: Int = 100,
    ): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(format, quality, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        val prefix = when (format) {
            Bitmap.CompressFormat.JPEG -> "data:image/jpeg;base64,"
            Bitmap.CompressFormat.PNG -> "data:image/png;base64,"
            else -> "data:image/jpeg;base64,"
        }

        return prefix + Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}
