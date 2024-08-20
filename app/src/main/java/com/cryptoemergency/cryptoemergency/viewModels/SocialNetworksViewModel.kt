package com.cryptoemergency.cryptoemergency.viewModels

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.api.database.AppDatabase
import com.cryptoemergency.cryptoemergency.repository.database.NetworkName
import com.cryptoemergency.cryptoemergency.repository.database.SocialNetworksEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SocialNetworksViewModel @Inject constructor(
    private val database: AppDatabase,
) : ViewModel() {
    val message = MutableStateFlow<String?>(null)

    private val _socialNetworks = MutableStateFlow<List<SocialNetworksEntity>?>(null)
    val socialNetworks = _socialNetworks.asStateFlow()

    private val _currentSocialNetwork = MutableStateFlow<List<SocialNetworksEntity>?>(null)
    val currentSocialNetwork = _currentSocialNetwork.asStateFlow()

    init {
        viewModelScope.launch {
            getAll()
        }
    }

    private suspend fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            _socialNetworks.value = database.socialNetworkDao().getAll()
        }
    }

    fun fetchSocialNetwork(name: NetworkName) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentSocialNetwork.value = database.socialNetworkDao().getNetworksByName(name)
        }
    }

    fun insertSocialNetwork(
        networkName: NetworkName,
        url: String,
        description: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                database.socialNetworkDao().insertNetwork(
                    SocialNetworksEntity(
                        networkName = networkName,
                        url = url,
                        description = description,
                    )
                )
            } catch (e: SQLiteConstraintException) {
                Log.d("ASD", "it's work")
                message.value = "Запись с такой ссылкой уже существует"
            }
        }
    }
}
