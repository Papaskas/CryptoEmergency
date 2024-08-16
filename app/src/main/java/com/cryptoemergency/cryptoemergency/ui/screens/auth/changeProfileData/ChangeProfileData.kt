package com.cryptoemergency.cryptoemergency.ui.screens.auth.changeProfileData

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.ui.common.ScrollableScreen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input

@Composable
fun ChangeProfileDataScreen(
    viewModel: ChangeProfileDataViewModel = hiltViewModel()
) {
    ScrollableScreen {
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
