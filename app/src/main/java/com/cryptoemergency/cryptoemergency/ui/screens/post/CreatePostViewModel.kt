package com.cryptoemergency.cryptoemergency.ui.screens.post

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.http.ApiResponse
import com.cryptoemergency.cryptoemergency.lib.Convert
import com.cryptoemergency.cryptoemergency.lib.getDefaultHttpMessage
import com.cryptoemergency.cryptoemergency.repository.requests.createPost.Media
import com.cryptoemergency.cryptoemergency.repository.requests.createPost.Request
import com.cryptoemergency.cryptoemergency.repository.requests.createPost.createPostRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    val descriptionInput = mutableStateOf(TextFieldValue())
    val selectedRatioOption = mutableStateOf(PhotoFormat.RATIO_1X1)
    val multipleIsActive = mutableStateOf(false)
    val isError = mutableStateOf(false)
    val columnCount = 4

    val message = MutableStateFlow<String?>(null)

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

    fun fetch() {
        viewModelScope.launch {
            val res = createPostRequest(context, Request(
                description = descriptionInput.value.text,
                media = selectedMedia.map {
                    Media(
                        type = "photo",
                        originalUrl = Convert.uriToBase64(it, context),
                    )
                }
            ))

            if(res is ApiResponse.Success) {
                message.value = "Пост успешно создан!"
            } else if(res is ApiResponse.Error) {
                message.value = getDefaultHttpMessage(context, res.status)
            }
        }
    }
}

enum class PhotoFormat(val title: String) {
    RATIO_1X1("1x1"),
    RATIO_3X4("3x4"),
    RATIO_16X9("16x9"),
}
