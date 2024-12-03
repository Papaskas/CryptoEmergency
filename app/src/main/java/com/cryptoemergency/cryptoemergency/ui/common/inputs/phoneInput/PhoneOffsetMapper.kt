package com.cryptoemergency.cryptoemergency.ui.common.inputs.phoneInput

import androidx.compose.ui.text.input.OffsetMapping

/**
 * @see <a href="https://stackoverflow.com/questions/71274129/phone-number-visual-transformation-in-jetpack-compose">Исходный код</a>
 * */
class PhoneOffsetMapper(
    private val mask: String,
    private val numberChar: Char,
) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int {
        return offset - mask.take(offset).count { it != numberChar }
    }
}
