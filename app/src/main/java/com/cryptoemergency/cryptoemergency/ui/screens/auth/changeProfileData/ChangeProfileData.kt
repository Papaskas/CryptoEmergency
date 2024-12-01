package com.cryptoemergency.cryptoemergency.ui.screens.auth.changeProfileData

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.DateInput
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input
import com.cryptoemergency.cryptoemergency.ui.common.inputs.InputSelectorBottomMenu
import com.cryptoemergency.cryptoemergency.ui.common.inputs.MultiLineInput
import com.cryptoemergency.cryptoemergency.ui.common.topBar.ScreenTopBar

@Composable
fun ChangeProfileDataScreen(
    viewModel: ChangeProfileDataViewModel = hiltViewModel()
) {
    val res = LocalContext.current.resources

    Screen(
        topBar = { ScreenTopBar(title = res.getString(R.string.change_profile)) }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(20.dp))

            EditAvatar()

            Spacer(Modifier.height(30.dp))

            Input(
                label = res.getString(R.string.your_name),
                value = viewModel.firstName,
            )
            Spacer(Modifier.height(10.dp))
            Input(
                label = res.getString(R.string.username),
                value = viewModel.username,
            )
            Spacer(Modifier.height(10.dp))
            MultiLineInput(
                label = res.getString(R.string.about_me),
                value = viewModel.aboutMe,
                minSymbols = 0,
                minLines = 1,
                maxLines = 5,
                maxSymbols = 100,
                hasError = remember { mutableStateOf(false) },
            )
            Spacer(Modifier.height(10.dp))
            Input(
                label = res.getString(R.string.specialization),
                value = viewModel.specialization,
            )
            Spacer(Modifier.height(10.dp))
            DateInput(
                label = res.getString(R.string.birthday),
                value = viewModel.birthday,
            )
            Spacer(Modifier.height(10.dp))
            InputSelectorBottomMenu(
                label = res.getString(R.string.language),
                selectedItem = viewModel.language,
                items = arrayOf(
                    res.getString(R.string.language__russian),
                    res.getString(R.string.language__english),
                )
            )
        }
    }
}

@Composable
private fun ColumnScope.EditAvatar() {
    Box(Modifier.align(Alignment.CenterHorizontally)) {
        Image(
            painter = painterResource(R.drawable.avatar_placeholder2),
            contentDescription = null,
            modifier = Modifier.size(90.dp),
        )
        Image(
            painter = painterResource(R.drawable.btn_edit),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 7.dp, y = 10.dp),
        )
    }
}
