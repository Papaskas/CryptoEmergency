package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.addSocialNetworksSection

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papaska.data.models.db.SocialNetworkModel
import com.papaska.domain.entity.socialNetwork.SocialNetworkEntity
import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import com.papaska.domain.useCases.db.socialNetwork.GetAllSocialNetworksByNameUseCase
import com.papaska.domain.useCases.db.socialNetwork.GetAllSocialNetworksUseCase
import com.papaska.domain.useCases.db.socialNetwork.InsertSocialNetworkUseCase
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
    private val _socialNetworks = MutableStateFlow<List<SocialNetworkModel>>(emptyList())
    val socialNetworks = _socialNetworks.asStateFlow()

    private val _selectedNetwork = mutableStateOf(SocialNetworkName.entries[0])
    val selectedNetwork: State<SocialNetworkName> = _selectedNetwork

    init {
        viewModelScope.launch {
            val res = getAllSocialNetworksUseCase()

            res?.let { entities ->
//                _socialNetworks.value = entities.map { entity ->
////                    SocialNetworkModel(
////                        id = entity.id,
////                        sonetworkName = entity.socialNetworkName,
////                        urlPrefix = entity.urlPrefix,
////                        url = mutableStateOf(TextFieldValue(text = entity.url)),
////                        description = entity.description?.let {
////                            mutableStateOf(TextFieldValue(text = it))
////                        }
////                    )
//                }
            }
        }
    }

    fun onValueChange(
        textFieldId: Int,
        model: SocialNetworkModel
    ) {
        val updatedList = socialNetworks.value.toMutableList()

        val index = updatedList.indexOfFirst {
            it.id == textFieldId
        }

        if(index != -1) {
            updatedList[index] = model
        }
        else {
            updatedList.add(model)
        }

        _socialNetworks.value = updatedList
    }

    fun addNewNetworkSection() {

    }

    fun removeAdditionalSocialNetwork() {
        viewModelScope.launch {  }
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
