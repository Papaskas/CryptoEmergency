package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.TitleSection

@Composable
fun SocialNetworks(
    viewModel: SocialNetworksViewModel = hiltViewModel()
) {
    val showBottomSheet = remember { mutableStateOf(false) }

    Column {
        TitleSection(title = "Социальные сети")
        EmptyProfilePage(
            title = "Социальных сетей пока нет",
            message = "Добавьте социальных сетей\nв свой профиль",
            buttonText = "Добавить социальную сеть",
            onClick = {
                showBottomSheet.value = true
            }
        )
    }

    BottomSheet(showBottomSheet = showBottomSheet, title = "Добавить социальную сеть") {
        val selectedOption = remember { mutableStateOf(socialNetworksIcons[0]) }

        SocialNetworksSelection(viewModel, selectedOption)
        AddSocialNetwork(viewModel, selectedOption)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SocialNetworksSelection(
    viewModel: SocialNetworksViewModel,
    selectedOption: MutableState<SocialNetworkIconType>,
) {
    FlowRow {
        socialNetworksIcons.forEach {
            SocialNetSelectableItem(
                it,
                selectedOption,
            )
        }
    }
}

@Composable
private fun SocialNetSelectableItem(
    socialNetwork: SocialNetworkIconType,
    selectedOption: MutableState<SocialNetworkIconType>,
) {
    val selected = selectedOption.value == socialNetwork

    IconButton(
        modifier = Modifier
            .selectable(
                selected = selected,
                onClick = { selectedOption.value = socialNetwork }
            ),
        onClick = { selectedOption.value = socialNetwork },
    ) {
        if(selected) {
            Icon(
                painter = painterResource(socialNetwork.activeIcon),
                contentDescription = socialNetwork.contentDescription,
                tint = Color.Unspecified,
            )
        } else {
            Icon(
                painter = painterResource(socialNetwork.icon),
                contentDescription = socialNetwork.contentDescription,
                tint = Theme.colors.text4,
            )
        }
    }
}

@Composable
private fun AddSocialNetwork(
    viewModel: SocialNetworksViewModel,
    selectedOption: MutableState<SocialNetworkIconType>
) {

}
