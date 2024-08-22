package com.cryptoemergency.cryptoemergency.ui.screens.auth.changeProfileData

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.ui.common.ScrollableScreen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input

@Composable
fun ChangeProfileDataScreen(
    viewModel: ChangeProfileDataViewModel = hiltViewModel()
) {
    ScrollableScreen {
        Spacer(Modifier.height(20.dp))

        EditAvatar(Modifier.align(Alignment.CenterHorizontally))

        Spacer(Modifier.height(30.dp))

        Input(
            label = "Ваше имя",
            value = viewModel.firstName,
        )
        Spacer(Modifier.height(10.dp))
        Input(
            label = "Имя пользователя",
            value = viewModel.username,
        )
        Spacer(Modifier.height(10.dp))
        Input(
            label = "О себе",
            value = viewModel.aboutMe,
        )
        Spacer(Modifier.height(10.dp))
        Input(
            label = "Специализация",
            value = viewModel.specialization,
        )
        Spacer(Modifier.height(10.dp))
        Input(
            label = "Дата рождения",
            value = viewModel.birthday,
        )
        Spacer(Modifier.height(10.dp))
        Input(
            label = "Язык",
            value = viewModel.language,
        )
    }
}

@Composable
private fun EditAvatar(modifier: Modifier) {
    Box(modifier) {
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
