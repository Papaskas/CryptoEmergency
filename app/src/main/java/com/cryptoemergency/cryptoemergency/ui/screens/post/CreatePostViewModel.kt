package com.cryptoemergency.cryptoemergency.ui.screens.post

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.data.http.ApiResponse
import com.cryptoemergency.cryptoemergency.lib.Convert
import com.cryptoemergency.cryptoemergency.lib.Convert.toBase64
import com.cryptoemergency.cryptoemergency.lib.Http
import com.cryptoemergency.cryptoemergency.lib.Redirect
import com.cryptoemergency.cryptoemergency.lib.vibrate
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.createPost.Media
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.createPost.Request
import com.cryptoemergency.cryptoemergency.api.domain.model.requests.createPost.createPostRequest
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.common.PhotoFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    val message = MutableStateFlow<String?>(null)
    val redirect = MutableStateFlow<Redirect?>(null)

    val columnCount = 4
    val currentStep = mutableIntStateOf(0)
    val isError = mutableStateOf(false)

    val descriptionInput = mutableStateOf(TextFieldValue())

    val selectedRatioOption = mutableStateOf(PhotoFormat.RATIO_1X1)
    val multipleIsActive = mutableStateOf(false)

    val mediaFiles = mutableStateListOf<Uri>()
    val selectedMedia = mutableStateListOf<Uri>()

    val commentsEnabled = mutableStateOf(true)
    val visualOnlySubs = mutableStateOf(false)

    val awaitServer = mutableStateOf(false)

    private fun toggleSoloMedia(media: Uri) {
        if (selectedMedia.isNotEmpty()) {
            selectedMedia[0] = media
        } else {
            selectedMedia.add(media)
        }
    }

    fun toggleMultipleMedia(media: Uri) {
        if (selectedMedia.contains(media)) {
            isError.value = false
            selectedMedia.remove(media)
        } else {
            if(selectedMedia.size > 4) {
                isError.value = true
                vibrate(context)
            } else {
                isError.value = false
                multipleIsActive.value = true
                selectedMedia.add(media)
            }
        }
    }

    fun toggleMedia(media: Uri) {
        if (multipleIsActive.value) {
            isError.value = false
            toggleMultipleMedia(media)
        } else {
            toggleSoloMedia(media)
        }
    }

    fun createPost() {
        viewModelScope.launch {
            awaitServer.value = true

            val res = createPostRequest(context, Request(
                description = descriptionInput.value.text,
                media = selectedMedia.map {
                    Media(
                        type = "photo",
                        originalUrl = it.toBase64(context, Bitmap.CompressFormat.JPEG),
                    )
                }
            ))

            if (res is ApiResponse.Success) {
                awaitServer.value = false

                message.value = "Пост успешно создан!" // TODO: translate
                redirect.value = Redirect(Destination.Home.Home)
            } else if(res is ApiResponse.Error) {
                awaitServer.value = false

                message.value = Http.getDefaultMessages(context, res.status)
            }
        }
    }
}
