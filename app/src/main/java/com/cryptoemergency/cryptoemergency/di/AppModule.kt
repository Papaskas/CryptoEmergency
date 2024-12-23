package com.cryptoemergency.cryptoemergency.di

import com.papaska.data.repositories.remote.NetworkRepositoryImpl
import com.papaska.domain.repositories.local.TokenRepository
import com.papaska.domain.repositories.remote.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(
        tokenRepository: TokenRepository
    ): NetworkRepository = NetworkRepositoryImpl(tokenRepository = tokenRepository)
}
