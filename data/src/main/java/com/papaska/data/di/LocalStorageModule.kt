package com.papaska.data.di

import android.content.Context
import com.papaska.data.qualifiers.PinCodeStorage
import com.papaska.data.qualifiers.TokenStorage
import com.papaska.data.repositories.local.PreferencesLocalStorageRepositoryImpl
import com.papaska.data.repositories.local.ProtoLocalStorageRepositoryImpl
import com.papaska.domain.entity.keys.KeyImpl
import com.papaska.domain.entity.keys.ProtoKeyImpl
import com.papaska.domain.useCases.storage.LocalStorageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class LocalStorageModule {

    @Provides
    @ViewModelScoped
    fun provideUserLocalStorage(
       @ApplicationContext context: Context,
    ) = LocalStorageUseCase(
        localStorageRepository = ProtoLocalStorageRepositoryImpl(
            context = context,
            key = ProtoKeyImpl.USER,
        )
    )

    @Provides
    @ViewModelScoped
    fun provideThemeLocalStorage(
        @ApplicationContext context: Context,
    ) = LocalStorageUseCase(
        localStorageRepository = ProtoLocalStorageRepositoryImpl(
            context = context,
            key = ProtoKeyImpl.THEME,
        )
    )

    @TokenStorage
    @Provides
    fun provideTokenLocalStorage(
        @ApplicationContext context: Context,
    ) = LocalStorageUseCase(
        localStorageRepository = PreferencesLocalStorageRepositoryImpl(
            context = context,
            key = KeyImpl.TOKEN
        )
    )

    @PinCodeStorage
    @Provides
    fun providePinCodeLocalStorage(
        @ApplicationContext context: Context,
    ) = LocalStorageUseCase(
        localStorageRepository = PreferencesLocalStorageRepositoryImpl(
            context = context,
            key = KeyImpl.PIN_CODE
        )
    )
}
