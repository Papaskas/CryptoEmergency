package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.lib.Listener
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.ScrollableScreen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.TitleSection
import com.cryptoemergency.cryptoemergency.viewModels.SocialNetworksViewModel

@Composable
fun SocialNetworks(
    viewModel: SocialNetworksViewModel = hiltViewModel()
) {
    val showBottomSheet = remember { mutableStateOf(false) }

    Listener(message = viewModel.message)

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

        ScrollableScreen(
            padding = PaddingValues(0.dp)
        ) {
            SocialNetworksSelector(selectedOption)
            AddSocialNetwork(viewModel, selectedOption)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnScope.SocialNetworksSelector(
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
                contentDescription = socialNetwork.networkName.name,
                tint = Color.Unspecified,
            )
        } else {
            Icon(
                painter = painterResource(socialNetwork.icon),
                contentDescription = socialNetwork.networkName.name,
                tint = Theme.colors.text4,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnScope.AddSocialNetwork(
    viewModel: SocialNetworksViewModel,
    selectedOption: MutableState<SocialNetworkIconType>
) {
    val socialNetworks = viewModel.socialNetworks.collectAsState()
    val filteredNetworks =
        socialNetworks.value.filter { it.networkName == selectedOption.value.networkName }.toMutableList()

    Spacer(Modifier.height(15.dp))

    FlowColumn {
        filteredNetworks.forEach { network ->
            Input(
                prefix = {
                    Text(
                        text = network.urlPrefix.value.text,
                    )
                },
                value = network.url,
                label = "Ссылка",
            )

            Spacer(Modifier.height(15.dp))

            Input(
                value = network.description,
                label = "Описание",
                singleLine = false,
            )

            Spacer(Modifier.height(15.dp))
        }
    }

    Text(
        text = "Добавить еще",
        style = Theme.typography.body1,
        color = Theme.colors.accent,
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) {
            viewModel.socialNetworks.value += SocialNetworkType(
                networkName = selectedOption.value.networkName,
                urlPrefix = mutableStateOf(TextFieldValue(filteredNetworks[0].urlPrefix.value.text)),
                url = mutableStateOf(TextFieldValue("")),
                description = mutableStateOf(TextFieldValue("")),
            )
        }
    )

    Spacer(Modifier.height(30.dp))

    CommonButton(
        onClick = {
//                viewModel.insertSocialNetwork(
//                    selectedOption.value.contentDescription,
//                    url.value.text,
//                    description.value.text,
//                )
        },
        text = "Добавить социальную сеть",
    )
}
