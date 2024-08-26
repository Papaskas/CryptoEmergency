package com.cryptoemergency.cryptoemergency.ui.screens.home.newsFeed

import android.net.Uri
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.cryptoemergency.cryptoemergency.model.NewsFeedItemProps
import com.cryptoemergency.cryptoemergency.model.NewsItemType
import com.cryptoemergency.cryptoemergency.ui.common.NewsFeedItem

@Composable
fun NewsFeedScreen() {
    val items = arrayOf(
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
        )
    )

    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(items.size) { index ->
            NewsFeedItem(items[index])
        }
    }
}
