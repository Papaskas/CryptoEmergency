package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

        SocialNetworksSelector(viewModel, selectedOption)
        AddSocialNetwork(viewModel, selectedOption)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SocialNetworksSelector(
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
                contentDescription = socialNetwork.contentDescription.name,
                tint = Color.Unspecified,
            )
        } else {
            Icon(
                painter = painterResource(socialNetwork.icon),
                contentDescription = socialNetwork.contentDescription.name,
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
    val currentSocialNetwork = viewModel.currentSocialNetwork.collectAsState()
//    val url = remember { mutableStateOf(currentSocialNetwork.value?.get(0)?.let { TextFieldValue(it.url) }) }
//    val description = remember { mutableStateOf(currentSocialNetwork.value?.get(0)?.let {
//        TextFieldValue(
//            it.description)
//    }) }
//    val socialNetwork = viewModel.socialNetworks.collectAsState()


//    LaunchedEffect(selectedOption.value) {
//        val network = Network.valueOf(selectedOption.value.contentDescription)
//        val res = viewModel.getSocialNetwork(network)
//
//        if (res!= null) {
//            url.value = TextFieldValue(text = res.data[0].url)
//            description.value = TextFieldValue(text = res.data[0].description)
//        } else {
//            url.value = TextFieldValue(text = "")
//            description.value = TextFieldValue(text = "")
//        }
//    }

    LaunchedEffect(selectedOption) {
        viewModel.fetchSocialNetwork(selectedOption.value.contentDescription)
        Log.d("res", "${currentSocialNetwork.value}")
    }

    if(currentSocialNetwork.value != null) {
        currentSocialNetwork.value!!.forEach {
            val url = remember { mutableStateOf(TextFieldValue(it.url)) }
            val description = remember { mutableStateOf(TextFieldValue(it.description)) }

            Input(
                value = url,
                label = "Ссылка",
            )

            Spacer(Modifier.height(15.dp))

            Input(
                value = description,
                label = "Описание",
                singleLine = false,
            )

            Spacer(Modifier.height(15.dp))

            CommonButton(
                onClick = {
                    viewModel.insertSocialNetwork(
                        selectedOption.value.contentDescription,
                        url.value.text,
                        description.value.text,
                    )
                },
                text = "Добавить социальную сеть",
            )
        }
    }

    Column {
        Spacer(Modifier.height(15.dp))

        Text(
            text = "Добавить еще",
            style = Theme.typography.body1,
            color = Theme.colors.accent,
        )
    }
}
