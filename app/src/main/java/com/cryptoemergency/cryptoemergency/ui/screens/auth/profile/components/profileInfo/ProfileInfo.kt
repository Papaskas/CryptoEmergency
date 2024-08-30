package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.profileInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileViewModel

/**
 * Выдвижное меню с подробной информацией о пользователе
 */
@Composable
fun ProfileInfo(
    showProfileInfo: MutableState<Boolean>,
    viewModel: ProfileViewModel,
) {
    val items = arrayOf(
        BioInfoType(viewModel.user.username,"Имя пользователя", true),
        BioInfoType(viewModel.user.statusText,"О себе"),
        BioInfoType(viewModel.user.specialization,"Специализация"),
        BioInfoType(viewModel.user.birthday,"Дата рождения"),
        BioInfoType(viewModel.user.language,"Язык"),
        BioInfoType(viewModel.user.dateOfRegistration,"Дата регистрации"),
    )

    BottomSheet(
        contentPadding = PaddingValues(0.dp),
        showBottomSheet = showProfileInfo,
        title = "Подробнее",
        actionIcon = {

        },
    ) {

        items.forEach {
            BioInfo(it)
        }
    }
}

@Composable
private fun BioInfo(
    prop: BioInfoType,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            Modifier.padding(Theme.values.padding)
        ) {
            Text(
                text = prop.title,
                color = if(prop.isUserName) Theme.colors.accent else Theme.colors.text1,
                style = Theme.typography.body1,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = prop.description,
                color = Theme.colors.text2,
                style = Theme.typography.caption2,
            )
        }

        if(prop.isUserName) {
            Spacer(Modifier.weight(1f))

            IconButton(onClick = { /*TODO*/ }) {
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