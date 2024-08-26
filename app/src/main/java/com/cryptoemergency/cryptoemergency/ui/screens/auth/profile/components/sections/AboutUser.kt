package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.profileInfo.ProfileInfo

/**
 * Блок основной информации о пользователе: имя, статус,
 * */
@Composable
fun AboutUser(viewModel: ProfileViewModel) {
    Column {
        Bio(viewModel)

        Spacer(Modifier.height(15.dp))

        Subscribes(viewModel)
    }
}

@Composable
private fun Bio(viewModel: ProfileViewModel) {
    val showProfileInfo = remember {
        mutableStateOf(false)
    }

    Row {
        Image(
            painter = painterResource(R.drawable.avatar_placeholder),
            contentDescription = "Уведомления",
            modifier = Modifier.size(70.dp)
        )

        Spacer(Modifier.width(16.dp))

        Column {
            Spacer(Modifier.height(15.dp) )

            Text(
                text = viewModel.user.name,
                style = Theme.typography.h2,
                color = Theme.colors.text1,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = viewModel.user.statusText,
                style = Theme.typography.caption1,
                color = Theme.colors.text2,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Подробнее",
                style = Theme.typography.caption1,
                color = Theme.colors.accent,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { showProfileInfo.value = true },
            )
        }
    }

    ProfileInfo(showProfileInfo, viewModel)
}

@Composable
private fun Subscribes(viewModel: ProfileViewModel) {
    Row {
        Column {
            Text(
                text = viewModel.user.countSubscribes.toString(),
                style = Theme.typography.subscribersCount,
                color = Theme.colors.text1,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Подписки",
                style = Theme.typography.caption1,
                color = Theme.colors.text2,
            )
        }
        Spacer(Modifier.width(15.dp))
        Column {
            Text(
                text = viewModel.user.countSubscribers.toString(),
                style = Theme.typography.subscribersCount,
                color = Theme.colors.text1,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Подписчики",
                style = Theme.typography.caption1,
                color = Theme.colors.text2,
            )
        }
    }
}
