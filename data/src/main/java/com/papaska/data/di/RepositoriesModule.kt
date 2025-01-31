package com.papaska.data.di

import android.content.Context
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.qualifiers.StorageQualifiers
import com.papaska.data.repositories.db.SocialNetworkRepositoryImpl
import com.papaska.data.repositories.local.PreferencesLocalStorageRepositoryImpl
import com.papaska.data.repositories.local.ProtoLocalStorageRepositoryImpl
import com.papaska.data.repositories.network.NetworkRepositoryImpl
import com.papaska.domain.entity.keys.KeyImpl
import com.papaska.domain.entity.keys.ProtoKeyImpl
import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.entity.local.UserEntity
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
    fun provideUserStorageRepository(
        @ApplicationContext context: Context
    ): LocalStorageRepository<UserEntity> = ProtoLocalStorageRepositoryImpl(
        context = context,
        key = ProtoKeyImpl.USER,
    )

    @Provides
    @Singleton
    fun provideThemeStorageRepository(
        @ApplicationContext context: Context
    ): LocalStorageRepository<ThemeEntity> = ProtoLocalStorageRepositoryImpl(
        context = context,
        key = ProtoKeyImpl.THEME,
    )

    @Provides
    @Singleton
    @StorageQualifiers.PinCodeStorageRepository
    fun providePinCodeStorageRepository(
        @ApplicationContext context: Context
    ): LocalStorageRepository<PinCodeEntity> = PreferencesLocalStorageRepositoryImpl(
        context = context,
        key = KeyImpl.PIN_CODE,
    )

    @Provides
    @Singleton
    @StorageQualifiers.TokenStorageRepository
    fun provideTokenStorageRepository(
        @ApplicationContext context: Context
    ): LocalStorageRepository<TokenEntity> = PreferencesLocalStorageRepositoryImpl(
        context = context,
        key = KeyImpl.TOKEN,
    )

    @Provides
    @Singleton
    fun provideNetworkRepository(
        @StorageQualifiers.TokenStorageRepository tokenStorage: LocalStorageRepository<TokenEntity>
    ): NetworkRepository = NetworkRepositoryImpl(
        tokenRepository = tokenStorage
    )

    @Provides
    @Singleton
    fun provideSocialNetworkRepository(
        socialNetworkDao: SocialNetworkDao
    ): SocialNetworkRepository = SocialNetworkRepositoryImpl(socialNetworkDao)
}