package com.cryptoemergency.cryptoemergency.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.store.ProtoStore
import com.cryptoemergency.cryptoemergency.repository.store.data.Network
import com.cryptoemergency.cryptoemergency.repository.store.data.SocialNetwork
import com.cryptoemergency.cryptoemergency.repository.store.data.SocialNetworks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialNetworksViewModel @Inject constructor(
    private val socialNetworkStore: ProtoStore<SocialNetworks>,
) : ViewModel() {
    val message = MutableStateFlow<String?>(null)

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

    suspend fun getSocialNetwork(
        network: Network,
    ): SocialNetwork? {
        val data = socialNetworkStore.get()
        return data.networks.find { it.network == network }
    }

    fun updateSocialNetwork(
        network: SocialNetwork,
    ) {
        viewModelScope.launch {
            Log.d("NET", "$network")
            val data = socialNetworkStore.get()
            val updatedNetworks = data.networks.map {
                if(it.network == network.network) {
                    Log.d("Updating: ", "${it.network}")
                    Log.d("Updating", "${it.data}")
                    it.copy(data = network.data)
                } else {
                    Log.d("Updating: ", "${it}")
                    it
                }
            }

            Log.d("updatedNetworks", "$updatedNetworks")
            val res = socialNetworkStore.dataStore.updateData {
                it.copy(networks = updatedNetworks)
            }
            Log.d("res", "$res")

            message.value = "Социальная сеть успешно изменена!"
        }
    }
}
