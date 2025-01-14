package com.cryptoemergency.cryptoemergency.ui.common.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.papaska.domain.entity.remote.post.PostEntity
import com.papaska.domain.entity.remote.post.PostViewEntity

@Composable
fun PostGrid(
    posts: List<PostEntity>,
) {
    Column {
        Row {
            PostGridItem(
                posts = posts,
                index = 0,
                height = 1.5f,
            )

            Spacer(Modifier.width(1.dp))

            Column {
                PostGridItem(posts, 1)
                Spacer(Modifier.height(1.dp))
                PostGridItem(posts, 2)
            }

            Spacer(Modifier.width(1.dp))

            Column {
                PostGridItem(posts, 3)
                Spacer(Modifier.height(1.dp))
                PostGridItem(posts, 4)
            }
        }

        Spacer(Modifier.height(1.dp))

        Row {
            Column {
                PostGridItem(posts, 5)
                Spacer(Modifier.height(1.dp))
                PostGridItem(posts, 6)
            }

            Spacer(Modifier.width(1.dp))

            PostGridItem(
                posts = posts,
                index = 7,
                width = 1.5f,
            )
        }
    }
}

@Composable
fun PostGridReverse(
    posts: List<PostEntity>,
) {
    Column {
        Spacer(Modifier.height(1.dp))

        Row {
            Column {
                PostGridItem(posts, 0)
                Spacer(Modifier.height(1.dp))
                PostGridItem(posts, 1)
            }

            Spacer(Modifier.width(1.dp))

            Column {
                PostGridItem(posts, 2)
                Spacer(Modifier.height(1.dp))
                PostGridItem(posts, 3)
            }

            Spacer(Modifier.width(1.dp))

            PostGridItem(
                posts = posts,
                index  = 4,
                width = 1.5f,
            )
        }

        Spacer(Modifier.height(1.dp))

        Row {
            PostGridItem(
                posts = posts,
                index  = 5,
                width = 1.5f,
            )

            Spacer(Modifier.width(1.dp))

            Column {
                PostGridItem(posts,6)
                Spacer(Modifier.height(1.dp))
                PostGridItem(posts,7)
            }
        }
    }
}

@Composable
private fun PostGridItem(
    posts: List<PostEntity>,
    index: Int,
    width: Float = 3f,
    height: Float = width,
) {
    if (posts.size < index + 1) return

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    PostItem(
        post = posts[index],
        viewType = PostViewEntity.SHORT,
        contentModifier = Modifier.size(
            width = screenWidth / width,
            height = screenWidth / height,
        ),
    )
}
