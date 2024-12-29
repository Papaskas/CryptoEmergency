package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.common.BaseUiState
import com.cryptoemergency.cryptoemergency.lib.Http
import com.papaska.core.entity.remote.post.PostsEntity
import com.papaska.core.http.ApiResponse
import com.papaska.core.useCases.remote.post.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getPostUseCase: GetPostUseCase,
) : ViewModel() {
    private val _posts = MutableStateFlow<PostsEntity?>(null)
    val posts = _posts.asStateFlow()

    private val _uiState = MutableStateFlow<BaseUiState>(BaseUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val res = getPostUseCase()

            if (res is ApiResponse.Success) {
                _posts.value = res.body
            } else if (res is ApiResponse.Error) {
                _uiState.value = BaseUiState.Error(Http.getDefaultMessages(context, res.status))
            }
        }
    }
}
