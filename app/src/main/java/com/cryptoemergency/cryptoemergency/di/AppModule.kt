package com.cryptoemergency.cryptoemergency.di

import com.cryptoemergency.cryptoemergency.BuildConfig
import com.papaska.core.entity.config.ServerConfiguration
import com.papaska.core.entity.http.DomainUrlProtocol
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
    fun provideConfig() =
        ServerConfiguration(
            port = BuildConfig.PORT,
            host = BuildConfig.HOST,
            protocol = DomainUrlProtocol.valueOf(BuildConfig.PROTOCOL),
        )
}
