package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.profileInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileViewModel

/**
 * Выдвижное меню с подробной информацией о пользователе
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfoBottomSheet(
    showProfileInfo: MutableState<Boolean>,
    viewModel: ProfileViewModel,
) {
    val locale = LocalLocale.current

    val items = arrayOf(
        BioInfoType(viewModel.user.username, locale.labels.username, true),
        BioInfoType(viewModel.user.statusText, locale.labels.aboutMe),
        BioInfoType(viewModel.user.specialization, locale.labels.specialization),
        BioInfoType(viewModel.user.birthday, locale.labels.birthday),
        BioInfoType(viewModel.user.language, locale.labels.language),
        BioInfoType(viewModel.user.dateOfRegistration, locale.labels.dateOfRegistration),
    )

    BottomSheet(
        contentPadding = 0.dp,
        showBottomSheet = showProfileInfo,
        title = locale.moreDetails,
        actionIcon = {},
    ) {
        items.forEach {
            BioInfo(
                title = it.title,
                isUserName = it.isUserName,
                description = it.description,
            )
        }
    }
}

@Composable
private fun BioInfo(
    title: String,
    isUserName: Boolean,
    description: String,
) {
    val navController = LocalNavController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            Modifier.padding(Theme.values.padding)
        ) {
            Text(
                text = title,
                color = if (isUserName) Theme.colors.accent else Theme.colors.text1,
                style = Theme.typography.body1,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = description,
                color = Theme.colors.text2,
                style = Theme.typography.caption2,
            )
        }

        if (isUserName) {
            Spacer(Modifier.weight(1f))

            IconButton(onClick = {
                navController.navigate(Routes.Page.QRCode(
                    text = title,
                ))
            }) {
                Icon(
                    painter = painterResource(R.drawable.qr_filled),
                    contentDescription = null,
                    tint = Theme.colors.accent,
                )
            }
        }
    }

    CommonHorizontalDivider()
}

private data class BioInfoType(
    val title: String,
    val description: String,
    val isUserName: Boolean = false,
)
