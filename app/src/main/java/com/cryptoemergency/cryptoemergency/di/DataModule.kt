package com.cryptoemergency.cryptoemergency.di

import android.content.Context
import com.papaska.data.dataSources.local.localStorage.LocalStorageDataSourceImpl
import com.papaska.data.dataSources.local.localStorage.ProtoLocalStorageDataSourceImpl
import com.papaska.data.infrastructure.local.datastore.keys.KeyImpl
import com.papaska.data.infrastructure.local.datastore.keys.ProtoKeyImpl
import com.papaska.data.repositories.local.PinCodeRepositoryImpl
import com.papaska.data.repositories.local.ThemeRepositoryImpl
import com.papaska.data.repositories.local.TokenRepositoryImpl
import com.papaska.data.repositories.local.UserRepositoryImpl
import com.papaska.domain.repositories.local.PinCodeRepository
import com.papaska.domain.repositories.local.ThemeRepository
import com.papaska.domain.repositories.local.TokenRepository
import com.papaska.domain.repositories.local.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context,
    ): UserRepository = UserRepositoryImpl(userDataSource = ProtoLocalStorageDataSourceImpl(
        key = ProtoKeyImpl.USER, context = context
    ))

    @Provides
    @Singleton
    fun provideThemeRepository(
        @ApplicationContext context: Context,
    ): ThemeRepository = ThemeRepositoryImpl(themeDataSource = ProtoLocalStorageDataSourceImpl(
        key = ProtoKeyImpl.THEME, context = context
    ))

    @Provides
    @Singleton
    fun provideTokenRepository(
        @ApplicationContext context: Context,
    ): TokenRepository = TokenRepositoryImpl(tokenDataSource = LocalStorageDataSourceImpl(
        key = KeyImpl.TOKEN, context = context
    ))

    @Provides
    @Singleton
    fun providePinCodeRepository(
        @ApplicationContext context: Context,
    ): PinCodeRepository = PinCodeRepositoryImpl(pinCodeDataSource = LocalStorageDataSourceImpl(
        key = KeyImpl.PIN_CODE, context = context
    ))
}