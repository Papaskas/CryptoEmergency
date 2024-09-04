package com.cryptoemergency.cryptoemergency.types

import android.net.Uri

data class NewsFeedItemProps(
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