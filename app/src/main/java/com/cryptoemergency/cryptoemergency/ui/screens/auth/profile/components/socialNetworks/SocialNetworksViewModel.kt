package com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.components.socialNetworks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.store.ProtoStore
import com.cryptoemergency.cryptoemergency.api.store.Store
import com.cryptoemergency.cryptoemergency.module.TokenStore
import com.cryptoemergency.cryptoemergency.repository.store.data.Network
import com.cryptoemergency.cryptoemergency.repository.store.data.NetworkData
import com.cryptoemergency.cryptoemergency.repository.store.data.SocialNetworks
import com.cryptoemergency.cryptoemergency.repository.store.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialNetworksViewModel @Inject constructor(
    private val socialNetworkStore: ProtoStore<SocialNetworks>,
    private val userStore: ProtoStore<User>,
    @TokenStore private val tokenStore: Store<String>,
) : ViewModel() {
    val socialNetworks = MutableStateFlow(SocialNetworks())

    init {
        viewModelScope.launch {
            getCacheSocialNetworks()
            fetchSocialNetworks()
        }
    }

    private suspend fun getCacheSocialNetworks() {
        //socialNetworks.value = socialNetworkStore.get()
    }

    private suspend fun fetchSocialNetworks() {

    }

    fun updateSocialNetworks(
        network: Network,
        data: List<NetworkData>,
    ) {
        viewModelScope.launch {
            val res = socialNetworkStore.get()
            val updatedNetworks = res.networks.map {
                if(it.network == network) {
                    it.copy(data = data)
                } else {
                    it
                }
            }

            socialNetworkStore.dataStore.updateData {
                it.copy(networks = updatedNetworks)
            }
        }
    }
}
