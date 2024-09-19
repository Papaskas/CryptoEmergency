package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(

) : ViewModel() {
    val selectedRatioOption = mutableStateOf(PhotoFormat.RATIO_1X1)
    val multipleIsActive = mutableStateOf(false)
    val isError = mutableStateOf(false)
    val columnCount = 4

    val selectedMedia = mutableStateListOf<Uri>()

    private fun toggleSoloMedia(media: Uri) {
        if (selectedMedia.isNotEmpty()) {
            selectedMedia[0] = media
        } else {
            selectedMedia.add(media)
        }
    }

    fun toggleMultipleMedia(media: Uri) {
        if (selectedMedia.contains(media)) {
            selectedMedia.remove(media)
        } else {
            selectedMedia.add(media)
            multipleIsActive.value = true
        }
    }

    fun toggleMedia(media: Uri) {
        if (multipleIsActive.value) {
           toggleMultipleMedia(media)
        } else {
           toggleSoloMedia(media)
        }
    }
}

enum class PhotoFormat(val title: String) {
    RATIO_1X1("1x1"),
    RATIO_3X4("3x4"),
    RATIO_16X9("16x9"),
}
