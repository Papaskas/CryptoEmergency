package com.papaska.data.di

import android.content.Context
import com.papaska.data.Infrastructure.local.datastore.DataStore
import com.papaska.data.Infrastructure.local.datastore.ProtoDataStore
import com.papaska.data.dataSources.local.pinCode.PinCodeDataSource
import com.papaska.data.dataSources.local.pinCode.PinCodeDataSourceImpl
import com.papaska.data.dataSources.local.theme.ThemeDataSource
import com.papaska.data.dataSources.local.theme.ThemeDataSourceImpl
import com.papaska.data.dataSources.local.token.TokenDataSource
import com.papaska.data.dataSources.local.token.TokenDataSourceImpl
import com.papaska.data.dataSources.local.user.UserDataSource
import com.papaska.data.dataSources.local.user.UserDataSourceImpl
import com.papaska.data.old.store.Keys
import com.papaska.data.old.store.ProtoKeys
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
    fun provideUserStorage(
        @ApplicationContext context: Context,
    ): UserDataSource = UserDataSourceImpl(ProtoDataStore(key = ProtoKeys.USER, context = context))

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataSource: UserDataSource
    ): UserRepository = UserRepositoryImpl(userDataSource = userDataSource)


    @Provides
    @Singleton
    fun provideTokenStorage(
        @ApplicationContext context: Context,
    ): TokenDataSource = TokenDataSourceImpl(DataStore(key = Keys.TOKEN, context = context))

    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenDataSource: TokenDataSource
    ): TokenRepository = TokenRepositoryImpl(tokenDataSource = tokenDataSource)


    @Provides
    @Singleton
    fun providePinCodeStorage(
        @ApplicationContext context: Context,
    ): PinCodeDataSource = PinCodeDataSourceImpl(DataStore(key = Keys.PIN_CODE, context = context))

    @Provides
    @Singleton
    fun providePinCodeRepository(
        pinCodeDataSource: PinCodeDataSource
    ): PinCodeRepository = PinCodeRepositoryImpl(pinCodeDataSource = pinCodeDataSource)


    @Provides
    @Singleton
    fun provideThemeStorage(
        @ApplicationContext context: Context,
    ): ThemeDataSource = ThemeDataSourceImpl(ProtoDataStore(key = ProtoKeys.THEME, context = context))

    @Provides
    @Singleton
    fun provideThemeRepository(
        themeDataSource: ThemeDataSource
    ): ThemeRepository = ThemeRepositoryImpl(themeDataSource = themeDataSource)
}