package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.common.BaseUiState
import com.cryptoemergency.cryptoemergency.lib.Convert.toBase64
import com.cryptoemergency.cryptoemergency.lib.Http
import com.cryptoemergency.cryptoemergency.lib.makeRequest
import com.cryptoemergency.cryptoemergency.lib.vibrate
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.UiState
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.common.PhotoFormat
import com.papaska.core.http.ApiResponse
import com.papaska.core.useCases.remote.post.CreatePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val createPostUseCase: CreatePostUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<BaseUiState>(BaseUiState.Idle)
    val uiState = _uiState.asStateFlow()

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
            makeRequest(
                context = context,
                onSuccess = {
                    _uiState.value = BaseUiState.Success("Пост успешно создан!")
                },
                onError = { _, message ->
                    _uiState.value = BaseUiState.Error(message)
                }
            ) {
                _uiState.value = BaseUiState.Loading

                createPostUseCase(CreatePostUseCase.Post(
                    description = descriptionInput.value.text,
                    media = selectedMedia.map {
                        CreatePostUseCase.Media(
                            type = "photo",
                            originalUrl = it.toBase64(context, Bitmap.CompressFormat.JPEG),
                        )
                    }
                ))
            }
        }
    }
}
