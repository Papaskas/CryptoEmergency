package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworksSection

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.entity.SocialNetworkData
import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.core.useCases.local.socialNetwork.GetAllSocialNetworksByNameUseCase
import com.papaska.core.useCases.local.socialNetwork.GetAllSocialNetworksUseCase
import com.papaska.core.useCases.local.socialNetwork.InsertSocialNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialNetworksViewModel @Inject constructor(
    private val getAllSocialNetworksUseCase: GetAllSocialNetworksUseCase,
    private val getAllSocialNetworksByNameUseCase: GetAllSocialNetworksByNameUseCase,
    private val insertSocialNetworkUseCase: InsertSocialNetworkUseCase,
) : ViewModel() {
    private val _socialNetworks = MutableStateFlow<List<SocialNetworkData>>(emptyList())
    val socialNetworks = _socialNetworks.asStateFlow()

    private val _selectedNetwork = mutableStateOf(SocialNetworkName.entries[0])
    val selectedNetwork: State<SocialNetworkName> = _selectedNetwork

    init {
        viewModelScope.launch {
            val res = getAllSocialNetworksUseCase()

            res?.let { entities ->
                _socialNetworks.value = entities.map { entity ->
                    SocialNetworkData(
                        id = entity.id,
                        networkName = entity.socialNetworkName,
                        urlPrefix = entity.urlPrefix,
                        url = mutableStateOf(TextFieldValue(text = entity.url)),
                        description = entity.description?.let {
                            mutableStateOf(TextFieldValue(text = it))
                        }
                    )
                }
            }
        }
    }

    /**
     * Кнопка `Add more`
     * */
    fun addNetworkSection() {

    }

    suspend fun getSocialNetworksByName(name: SocialNetworkName) =
        getAllSocialNetworksByNameUseCase(name)

    fun changeSelectedSocialNetwork(name: SocialNetworkName) {
        _selectedNetwork.value = name
    }

    fun saveSocialNetwork(socialNetworkEntity: SocialNetworkEntity) {
        viewModelScope.launch {
            insertSocialNetworkUseCase(socialNetworkEntity)
        }
//        _socialNetworks.value += SocialNetwork(
//            networkName = selectedOption.value.networkName,
//            urlPrefix = mutableStateOf(TextFieldValue(filteredNetworks[0].urlPrefix.value.text)),
//            url = mutableStateOf(TextFieldValue("")),
//            description = mutableStateOf(TextFieldValue("")),
//        )
    }
}
