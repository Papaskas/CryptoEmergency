package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.http.ApiResponse
import com.cryptoemergency.cryptoemergency.repository.requests.getToken.getTokenRequest
import com.cryptoemergency.cryptoemergency.types.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.types.NewsItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    init {
        viewModelScope.launch {
            val requestToken = getTokenRequest(context)
            if(requestToken is ApiResponse.Success) {
                requestToken.body.message
            }
        }
    }

    val items = arrayOf(
        NewsFeedItemProps(
            media = listOf(
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            ),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            ),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            ),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            ),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            ),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
                Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            ),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
        NewsFeedItemProps(
            media = listOf(Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380")),
            authorName = "Александр Криптовалюта",
            avatar = Uri.parse("https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946453.jpg?t=st=1724417216~exp=1724420816~hmac=adebada5671e7769a280f9b52dc72d398834775955cbcf4813db18dd472cbbc7&w=1380"),
            createDate = "2022-12-12",
            type = NewsItemType.FULL,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id ipsum " +
                    "vel felis consectetur convallis. Sed vel tempor lectus. Donec vel ipsum et ligula " +
                    "pulvinar consectetur. Pellentesque habitant morbi tristique senectus et netus et malesu"
        ),
    )
}
