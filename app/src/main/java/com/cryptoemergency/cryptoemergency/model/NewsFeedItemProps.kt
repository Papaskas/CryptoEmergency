package com.cryptoemergency.cryptoemergency.model

import android.net.Uri

data class NewsFeedItem(
    val media: List<Uri>,
    val avatar: Uri,
    val authorName: String,
    val createDate: String,
    val type: NewsItemType,
    val description: String? = null,
)

enum class NewsItemType {
    FULL, SHORT
}
