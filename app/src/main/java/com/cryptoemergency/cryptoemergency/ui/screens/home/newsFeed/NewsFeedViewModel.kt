package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.http.ApiResponse
import com.cryptoemergency.cryptoemergency.lib.getDefaultHttpMessage
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.PostsResponse
import com.cryptoemergency.cryptoemergency.repository.requests.getPosts.getPostsRequest
import com.cryptoemergency.cryptoemergency.repository.requests.getToken.getTokenRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val _posts = MutableStateFlow<PostsResponse?>(null)
    val posts = _posts.asStateFlow()

    val message = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            val res = getPostsRequest(context)

            if (res is ApiResponse.Success) {
                _posts.value = res.body
            } else if (res is ApiResponse.Error) {
                message.value = getDefaultHttpMessage(context, res.status)
            }
        }
    }
}
