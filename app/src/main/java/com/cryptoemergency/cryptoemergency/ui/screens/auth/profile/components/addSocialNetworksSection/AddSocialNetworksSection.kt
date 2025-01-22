package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworksSection

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import com.cryptoemergency.cryptoemergency.ui.common.BottomSheet
import com.cryptoemergency.cryptoemergency.ui.common.CommonHorizontalDivider
import com.cryptoemergency.cryptoemergency.ui.common.buttons.CommonButton
import com.cryptoemergency.cryptoemergency.ui.common.inputs.Input
import com.cryptoemergency.cryptoemergency.ui.common.inputs.MultiLineInput
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.EmptyProfilePage
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.common.TitleSection
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.entity.SocialNetworkIcons
import com.papaska.domain.entity.socialNetwork.SocialNetworkEntity
import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import com.papaska.domain.entity.socialNetwork.SocialNetworkType
import com.papaska.domain.utility.findUrlPrefix
import com.papaska.data.models.db.SocialNetworkModel

/**
 * Блок в секции content - Социальные сети
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSocialNetworksSection(
    vm: SocialNetworksViewModel = hiltViewModel()
) {
    val res = LocalContext.current.resources
    val showBottomSheet = remember { mutableStateOf(false) }

    Column {
        TitleSection(title = res.getQuantityString(R.plurals.social_network, 3))
        EmptyProfilePage(
            title = res.getString(
                R.string.empty,
                res.getQuantityString(R.plurals.social_network, 5),
            ),
            message = res.getString(
                R.string.empty,
                res.getQuantityString(R.plurals.social_network, 5),
            ),
            buttonText = res.getString(
                R.string.add,
                res.getQuantityString(R.plurals.social_network, 3).lowercase(),
            ),
            onClick = {
                showBottomSheet.value = true
            }
        )
    }

    BottomSheet(
        showBottomSheet = showBottomSheet,
        title = res.getQuantityString(R.plurals.social_network, 3)
    ) {
        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            SocialNetworksSelector(vm)
            Spacer(Modifier.height(15.dp))
            MainInputsSocialNetwork(vm)
            AdditionallyInputSocialNetworks(vm)
            Spacer(Modifier.height(15.dp))
            ButtonAddMore(vm)
            Spacer(Modifier.height(30.dp))
            ButtonSaveSocialNetwork(vm)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SocialNetworksSelector(
    vm: SocialNetworksViewModel
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        SocialNetworkName.entries
            .filter { it != SocialNetworkName.NONE }
            .forEach {
                SelectableItem(
                    selected = vm.selectedNetwork.value == it,
                    networkName = it,
                ) { vm.changeSelectedSocialNetwork(it) }
        }
    }
}

@Composable
private fun SelectableItem(
    selected: Boolean,
    networkName: SocialNetworkName,
    changeSelectedSocialNetwork: () -> Unit,
) {
    val socialNetworkIcons = SocialNetworkIcons.findByName(networkName)

    AnimatedContent(
        targetState = selected,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 150)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 100))
        },
        label = "",
    ) { targetState ->
        IconButton(
            modifier = Modifier
                .selectable(
                    selected = targetState,
                    onClick = changeSelectedSocialNetwork
                ),
            onClick = changeSelectedSocialNetwork,
        ) {
            Icon(
                painter = painterResource(
                    if (targetState) socialNetworkIcons.activeIcon else socialNetworkIcons.icon
                ),
                contentDescription = socialNetworkIcons.networkName.name,
                tint = if (targetState) Color.Unspecified else Theme.colors.text4,
            )
        }
    }
}

@Composable
private fun MainInputsSocialNetwork(
    vm: SocialNetworksViewModel
) {
    var filteredNetwork by remember { mutableStateOf<SocialNetworkEntity?>(null) }

    LaunchedEffect(vm.selectedNetwork.value) {
        filteredNetwork = vm
            .getSocialNetworksByName(vm.selectedNetwork.value)
            ?.filter { it.type == SocialNetworkType.MAIN }
            ?.getOrNull(0)
    }

    InputTemplate(
        vm = vm,
        networkType = SocialNetworkType.MAIN,
        url = filteredNetwork?.url ?: "",
        description = filteredNetwork?.description ?: "",
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AdditionallyInputSocialNetworks(
    vm: SocialNetworksViewModel
) {
    var filteredNetwork by remember { mutableStateOf<List<SocialNetworkEntity>?>(null) }

    LaunchedEffect(vm.selectedNetwork.value) {
        filteredNetwork = vm
            .getSocialNetworksByName(vm.selectedNetwork.value)
            ?.filter { it.type == SocialNetworkType.ADDITIONALLY }
    }

    FlowColumn {
        filteredNetwork?.takeIf { it.isNotEmpty() }?.let {
            repeat(it.size) { item ->
                CommonHorizontalDivider(Modifier.height(15.dp))

                Row {
                    Text("Дополнительная ссылка")

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = "Удалить",
                        color = Theme.colors.accent,
                        modifier = Modifier.clickable { vm.removeAdditionalSocialNetwork() }
                    )
                }

//                InputTemplate(
//                    urlPrefix = it[item].urlPrefix,
//                    url = it[item].url,
//                    description = it[item].description,
//                    networkName = vm.selectedNetwork.value,
//                )

                CommonHorizontalDivider(Modifier.height(15.dp))
            }
        }
    }
}

@Composable
private fun InputTemplate(
    vm: SocialNetworksViewModel,
    networkType: SocialNetworkType,
    url: String,
    description: String?,
) {
    val res = LocalContext.current.resources

    var data by remember { mutableStateOf<SocialNetworkModel?>(null) }

    val descriptionValue = remember { mutableStateOf(TextFieldValue(data?.description ?: "")) }
    val urlValue = remember { mutableStateOf(TextFieldValue(data?.url ?: "")) }

    LaunchedEffect(vm.selectedNetwork.value) {
        data = vm.socialNetworks.value.find {
            it.socialNetworkName == vm.selectedNetwork.value
            && it.url == url
            && it.description == description
            && it.type == networkType
        }
    }

    Input(
        prefix = { Text(text = findUrlPrefix(vm.selectedNetwork.value)) },
        isRequired = true,
        label = res.getString(R.string.url),
        value = urlValue,
        onValueChange = {
//            vm.onValueChange(SocialNetworkModel(
//                id = 0,
//                socialNetworkName = vm.selectedNetwork.value,
//                url = urlValue.value.text,
//                description = descriptionValue.value.text,
//                type = networkType
//            ))

            urlValue.value = it
        },
    )

    Spacer(Modifier.height(15.dp))

    MultiLineInput(
        value = descriptionValue,
        onValueChange = {
            descriptionValue.value = it

//            vm.onValueChange(SocialNetworkModel(
//                id = 0,
//                socialNetworkName = vm.selectedNetwork.value,
//                url = urlValue.value.text,
//                description = descriptionValue.value.text,
//                type = networkType
//            ))
        },
        label = res.getString(R.string.description),
        hasError = remember { mutableStateOf(false) },
        maxSymbols = 100,
        minSymbols = 0,
        maxLines = 3,
        minLines = 1,
    )
}

@Composable
private fun ButtonAddMore(
    vm: SocialNetworksViewModel
) {
    val res = LocalContext.current.resources

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) { vm.addNewNetworkSection() }
    ) {
        Icon(
            painter = painterResource(R.drawable.added_btn_plus),
            contentDescription = res.getString(R.string.add),
            tint = Theme.colors.accent,
        )

        Text(
            text = res.getString(
                R.string.add,
                res.getString(R.string.more).lowercase()
            ),
            style = Theme.typography.body1,
            color = Theme.colors.accent,
        )
    }
}

@Composable
private fun ButtonSaveSocialNetwork(
    vm: SocialNetworksViewModel,
) {
    val res = LocalContext.current.resources

    CommonButton(
        onClick = {
            vm.saveSocialNetwork(
                SocialNetworkEntity(
                    id = 0,
                    socialNetworkName = vm.selectedNetwork.value,
                    urlPrefix = "",
                    url = "",
                    description = "",
                    type = SocialNetworkType.MAIN,
                )
            )
//                viewModel.insertSocialNetwork(
//                    selectedOption.value.contentDescription,
//                    url.value.text,
//                    description.value.text,
//                )
        },
        text = res.getString(
            R.string.add,
            res.getQuantityString(R.plurals.social_network, 3).lowercase(),
        ),
    )
}
