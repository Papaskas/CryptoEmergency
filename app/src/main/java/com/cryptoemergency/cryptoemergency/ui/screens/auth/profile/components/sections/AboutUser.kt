package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileViewModel
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.moreDetails.MoreDetailsBottomSheet

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
    val res = LocalContext.current.resources
    val showProfileInfo = remember { mutableStateOf(false) }

    Box {
        Row {
            Image(
                painter = painterResource(R.drawable.avatar_placeholder),
                contentDescription = viewModel.user.name,
                modifier = Modifier.size(70.dp)
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Spacer(Modifier.height(15.dp))

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
                    text = res.getString(R.string.more_details),
                    style = Theme.typography.caption1,
                    color = Theme.colors.accent,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { showProfileInfo.value = true },
                )
            }
        }

        MoreDetailsBottomSheet(showProfileInfo, viewModel)
    }
}

@Composable
private fun Subscribes(viewModel: ProfileViewModel) {
    val res = LocalContext.current.resources

    Row {
        Column {
            Text(
                text = viewModel.user.countSubscribers.toString(),
                style = Theme.typography.subscribesCount,
                color = Theme.colors.text1,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = res.getString(R.string.subscribers),
                style = Theme.typography.caption1,
                color = Theme.colors.text2,
            )
        }
        Spacer(Modifier.width(15.dp))
        Column {
            Text(
                text = viewModel.user.countSubscriptions.toString(),
                style = Theme.typography.subscribesCount,
                color = Theme.colors.text1,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = res.getString(R.string.subscriptions),
                style = Theme.typography.caption1,
                color = Theme.colors.text2,
            )
        }
    }
}
