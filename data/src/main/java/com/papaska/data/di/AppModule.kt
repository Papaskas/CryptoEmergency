package com.papaska.data.di

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.entity.http.DomainUrlProtocol
import com.papaska.data.BuildConfig
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
