package com.cryptoemergency.cryptoemergency.ui.screens.createPost.modifyPost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.MultiLineInput
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.CreatePostViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.createPost.modifyPost.components.MediaPager

@Composable
fun ModifyPostScreen(viewModel: CreatePostViewModel) {
    Screen(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        topBar = { ScreenTopBar(
            title = "Создание поста", // TODO: перевод
            actions = { ActionsTopBar() },
            navigationIcon = {},
        ) },
        bottomBar = {},
    ) {
        Column(Modifier.padding(it)) {
            MediaPager(viewModel.selectedMedia)
            MultiLineInput(
                value = remember { mutableStateOf(TextFieldValue()) },
                isError = remember { mutableStateOf(false) },
                label = "Текст", // TODO: translate
                minLines = 1,
                maxLines = 15,
                maxSymbols = 10000,
            )
        }
    }
}

@Composable
private fun ActionsTopBar() {
    val navController = LocalNavController.current

    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            painter = painterResource(R.drawable.close),
            contentDescription = "Close", // TODO: добавить перевод
            tint = Theme.colors.text1,
        )
    }
}
