package com.cryptoemergency.cryptoemergency.lib

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat

/**
 *
 * Вызов вибрации устройства
 *
 * @param context контекст приложения
 *
 * @param milliseconds длительность вибрации в миллисекундах
 *
 * */
fun vibrate(
    context: Context,
    milliseconds: Long = 100,
) {
    val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)

    vibrator?.let {
        if (it.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val effect =
                    VibrationEffect.createOneShot(
                        milliseconds,
                        VibrationEffect.DEFAULT_AMPLITUDE,
                    )
                it.vibrate(effect)
            } else {
                // Используем устаревший метод вибрации для API ниже 26
                it.vibrate(milliseconds)
            }
        }
    }
}
