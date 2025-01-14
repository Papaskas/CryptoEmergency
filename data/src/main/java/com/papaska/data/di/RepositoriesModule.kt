package com.papaska.data.di

import android.content.Context
import com.papaska.domain.entity.keys.KeyImpl
import com.papaska.domain.entity.keys.ProtoKeyImpl
import com.papaska.domain.repositories.local.db.SocialNetworkRepository
import com.papaska.domain.repositories.local.storage.PinCodeRepository
import com.papaska.domain.repositories.local.storage.ThemeRepository
import com.papaska.domain.repositories.local.storage.TokenRepository
import com.papaska.domain.repositories.local.storage.UserRepository
import com.papaska.domain.repositories.remote.NetworkRepository
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.dataSources.localStorage.LocalStorageDataSourceImpl
import com.papaska.data.dataSources.localStorage.ProtoLocalStorageDataSourceImpl
import com.papaska.data.repositories.db.SocialNetworkRepositoryImpl
import com.papaska.data.repositories.local.PinCodeRepositoryImpl
import com.papaska.data.repositories.local.ThemeRepositoryImpl
import com.papaska.data.repositories.local.TokenRepositoryImpl
import com.papaska.data.repositories.local.UserRepositoryImpl
import com.papaska.data.repositories.network.NetworkRepositoryImpl
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
    fun provideUserRepository(
        @ApplicationContext context: Context,
    ): UserRepository = UserRepositoryImpl(
        userDataSource = ProtoLocalStorageDataSourceImpl(
            key = ProtoKeyImpl.USER,
            context = context
        )
    )

    @Provides
    @Singleton
    fun provideThemeRepository(
        @ApplicationContext context: Context,
    ): ThemeRepository = ThemeRepositoryImpl(
        themeDataSource = ProtoLocalStorageDataSourceImpl(
            key = ProtoKeyImpl.THEME,
            context = context
        )
    )

    @Provides
    @Singleton
    fun provideTokenRepository(
        @ApplicationContext context: Context,
    ): TokenRepository = TokenRepositoryImpl(
        tokenDataSource = LocalStorageDataSourceImpl(
            key = KeyImpl.TOKEN,
            context = context
        )
    )

    @Provides
    @Singleton
    fun providePinCodeRepository(
        @ApplicationContext context: Context,
    ): PinCodeRepository = PinCodeRepositoryImpl(
        pinCodeDataSource = LocalStorageDataSourceImpl(
            key = KeyImpl.PIN_CODE,
            context = context
        )
    )

    @Provides
    @Singleton
    fun provideNetworkRepository(
        tokenRepository: TokenRepository
    ): NetworkRepository = NetworkRepositoryImpl(tokenRepository = tokenRepository)

    @Provides
    @Singleton
    fun provideSocialNetworkRepository(
        socialNetworkDao: SocialNetworkDao
    ): SocialNetworkRepository = SocialNetworkRepositoryImpl(socialNetworkDao)
}