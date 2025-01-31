package com.papaska.data.di

import com.papaska.data.qualifiers.StorageQualifiers
import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.entity.local.UserEntity
import com.papaska.domain.repositories.local.storage.LocalStorageRepository
import com.papaska.domain.useCases.storage.LocalStorageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class LocalStorageModule {

    @Provides
    @ViewModelScoped
    fun provideUserLocalStorage(
       localStorageRepository: LocalStorageRepository<UserEntity>,
    ) = LocalStorageUseCase(
        localStorageRepository = localStorageRepository
    )

    @Provides
    @ViewModelScoped
    fun provideThemeLocalStorage(
        localStorageRepository: LocalStorageRepository<ThemeEntity>,
    ) = LocalStorageUseCase(
        localStorageRepository = localStorageRepository
    )

    @Provides
    @ViewModelScoped
    @StorageQualifiers.TokenStorage
    fun provideTokenLocalStorage(
        @StorageQualifiers.TokenStorageRepository localStorageRepository: LocalStorageRepository<TokenEntity>,
    ) = LocalStorageUseCase(
        localStorageRepository = localStorageRepository
    )

    @Provides
    @ViewModelScoped
    @StorageQualifiers.PinCodeStorage
    fun providePinCodeLocalStorage(
        @StorageQualifiers.PinCodeStorageRepository localStorageRepository: LocalStorageRepository<PinCodeEntity>
    ) = LocalStorageUseCase(
        localStorageRepository = localStorageRepository
    )
}
