package com.cryptoemergency.cryptoemergency.di

import com.cryptoemergency.cryptoemergency.BuildConfig
import com.papaska.core.entity.config.ServerConfiguration
import com.papaska.core.http.DomainUrlProtocol
import com.papaska.data.repositories.network.NetworkRepositoryImpl
import com.papaska.core.repositories.local.storage.TokenRepository
import com.papaska.core.repositories.remote.NetworkRepository
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
    fun provideConfig(): ServerConfiguration {
        return ServerConfiguration(
            port = BuildConfig.PORT,
            host = BuildConfig.HOST,
            protocol = DomainUrlProtocol.valueOf(BuildConfig.PROTOCOL),
        )
    }
}
