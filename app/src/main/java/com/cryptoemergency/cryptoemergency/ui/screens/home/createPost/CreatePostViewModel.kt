package com.cryptoemergency.cryptoemergency.ui.screens.home.createPost

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(

) : ViewModel() {
    val columnCount = 4
    val currentPhoto = R.drawable.banner1

    val selectionMedia = mutableSetOf<Uri>()
    val multipleSelectionIsActive = mutableStateOf(false)

    val selectedRatioOption = mutableStateOf(PhotoFormat.RATIO_1X1)
}

enum class PhotoFormat(val title: String) {
    RATIO_1X1("1x1"),
    RATIO_3X4("3x4"),
    RATIO_16X9("16x9"),
}
