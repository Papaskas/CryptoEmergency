package com.cryptoemergency.cryptoemergency.di

import com.cryptoemergency.cryptoemergency.BuildConfig
import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.http.DomainUrlProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConfigurationModule {

    @Provides
    fun provideConfig(): ServerConfiguration {
        return ServerConfiguration(
            port = BuildConfig.PORT,
            host = BuildConfig.HOST,
            protocol = DomainUrlProtocol.valueOf(BuildConfig.PROTOCOL),
        )
    }
}