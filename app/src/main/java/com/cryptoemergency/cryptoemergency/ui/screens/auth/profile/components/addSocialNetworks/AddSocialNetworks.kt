package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.cryptoemergency.cryptoemergency.providers.locale.LocalLocale
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.common.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input
import com.cryptoemergency.cryptoemergency.ui.common.inputs.MultiLineInput
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.TitleSection
import com.cryptoemergency.cryptoemergency.viewModels.SocialNetworksViewModel

/**
 * Блок в секции content - Социальные сети
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSocialNetworks(
    viewModel: SocialNetworksViewModel = hiltViewModel()
) {
    val locale = LocalLocale.current
    val showBottomSheet = remember { mutableStateOf(false) }

    Listener(message = viewModel.message)

    Column {
        TitleSection(title = locale.socialNetworks)
        EmptyProfilePage(
            title = locale.socialNetworksSection.emptyTitle,
            message = locale.socialNetworksSection.emptyDescription,
            buttonText = locale.socialNetworksSection.addSocialNetwork,
            onClick = {
                showBottomSheet.value = true
            }
        )
    }

    BottomSheet(showBottomSheet = showBottomSheet, title = locale.titles.addSocialNetworks) {
        val selectedOption = remember { mutableStateOf(socialNetworksIcons[0]) }

        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            SocialNetworksSelector(selectedOption)
            AddSocialNetwork(viewModel, selectedOption)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SocialNetworksSelector(
    selectedOption: MutableState<SocialNetworkIconType>,
) {
    FlowRow {
        socialNetworksIcons.forEach {
            SelectableItem(
                it,
                selectedOption,
            )
        }
    }
}

@Composable
private fun SelectableItem(
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
        if (selected) {
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
private fun AddSocialNetwork(
    viewModel: SocialNetworksViewModel,
    selectedOption: MutableState<SocialNetworkIconType>
) {
    val locale = LocalLocale.current
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
                isRequired = true,
                value = network.url,
                label = locale.url,
            )

            Spacer(Modifier.height(15.dp))

            MultiLineInput(
                value = network.description,
                label = locale.description,
                isError = mutableStateOf(false),
                maxSymbols = 100,
                maxLines = 3,
                minLines = 1,
            )

            Spacer(Modifier.height(15.dp))
        }
    }

    Text(
        text = locale.socialNetworksSection.addMore,
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
        text = locale.titles.addSocialNetworks,
    )
}