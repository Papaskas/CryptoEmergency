package com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.steps

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.buttons.ButtonType
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.CommonSwitch
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.MultiLineInput
import com.cryptoemergency.cryptoemergency.ui.screens.post.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.MediaPager
import com.cryptoemergency.cryptoemergency.ui.screens.post.createPost.components.TopBar

@Composable
fun SecondStep(
    viewModel: CreatePostViewModel,
) {
    Screen(
        topBar = { TopBar() },
        bottomBar = {},
        horizontalPadding = 0.dp,
        bottomSpacing = 0.dp,
    ) {
        Column(Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
        ) {
            MediaPager(viewModel.selectedMedia)
            Field(viewModel)
            Spacer(Modifier.height(35.dp))
            Toolbar()
            Spacer(Modifier.height(15.dp))
            Settings()
            Spacer(Modifier.height(15.dp))
            Bottom(viewModel)
        }
    }
}

@Composable
private fun Field(viewModel: CreatePostViewModel) {
    MultiLineInput(
        value = viewModel.descriptionInput,
        isError = remember { mutableStateOf(false) },
        label = "Текст", // TODO: translate
        minLines = 1,
        maxLines = 15,
        maxSymbols = 10000,
        modifier = Modifier.padding(horizontal = Theme.dimens.padding)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Toolbar() {
    val res = LocalContext.current.resources

    data class Item(
        @DrawableRes val icon: Int,
        @StringRes val title: Int,
        val onClick: () -> Unit,
    )

    val items = listOf(
        Item(R.drawable.image, R.string.image) {},
        Item(R.drawable.video, R.string.video) {},
        Item(R.drawable.geo, R.string.place) {},
        Item(R.drawable.people, R.string.mark_people) {},
        Item(R.drawable.themes, R.string.themes) {},
    )

    FlowRow(
        Modifier.padding(horizontal = Theme.dimens.padding)
    ) {
        repeat(items.size) {
            IconButton(
                onClick = items[it].onClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Theme.colors.surface3)
                    .size(50.dp),
            ) {
                Icon(
                    painter = painterResource(items[it].icon),
                    contentDescription = res.getString(items[it].title),
                )
            }
            Spacer(Modifier.width(10.dp))
        }
    }
}

@Composable
private fun Settings() {
    val checkedState = remember { mutableStateOf(true) }

    CommonHorizontalDivider()
    CommonSwitch(
        modifier = Modifier.padding(horizontal = Theme.dimens.padding),
        state = checkedState,
        text = "Комментарии к посту" // TODO: translate
    )
    CommonHorizontalDivider()
    CommonSwitch(
        modifier = Modifier.padding(horizontal = Theme.dimens.padding),
        state = checkedState,
        text = "Видны только подписчикам" // TODO: translate
    )
    CommonHorizontalDivider()
}

@Composable
private fun Bottom(viewModel: CreatePostViewModel) {
    Column(
        Modifier
            .padding(horizontal = Theme.dimens.padding)
            .padding(bottom = 35.dp)
    ) {
        CommonButton(
            onClick = {
                viewModel.fetch()
            },
            text = "Опубликовать",
        )
        Spacer(Modifier.height(10.dp))
        CommonButton(
            onClick = {},
            text = "Предпросмотр",
            buttonType = ButtonType.Secondary
        )
    }
}
