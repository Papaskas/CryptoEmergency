package com.papaska.data.di

import android.content.Context
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.qualifiers.TokenStorage
import com.papaska.data.repositories.db.SocialNetworkRepositoryImpl
import com.papaska.data.repositories.local.PreferencesLocalStorageRepositoryImpl
import com.papaska.data.repositories.network.NetworkRepositoryImpl
import com.papaska.domain.entity.keys.KeyImpl
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.db.SocialNetworkRepository
import com.papaska.domain.repositories.local.storage.LocalStorageRepository
import com.papaska.domain.repositories.remote.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(
        @ApplicationContext context: Context
    ): NetworkRepository = NetworkRepositoryImpl(
        tokenRepository = PreferencesLocalStorageRepositoryImpl(
            context = context,
            key = KeyImpl.TOKEN
        )
    )

    @Provides
    @Singleton
    fun provideSocialNetworkRepository(
        socialNetworkDao: SocialNetworkDao
    ): SocialNetworkRepository = SocialNetworkRepositoryImpl(socialNetworkDao)
}