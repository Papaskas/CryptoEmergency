package com.cryptoemergency.cryptoemergency.di

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.repositories.remote.NetworkRepository
import com.papaska.domain.useCases.remote.auth.LoginUseCase
import com.papaska.domain.useCases.remote.auth.RecoveryPasswordUseCase
import com.papaska.domain.useCases.remote.auth.RegisterUseCase
import com.papaska.domain.useCases.remote.post.CreatePostUseCase
import com.papaska.domain.useCases.remote.post.GetPostUseCase
import com.papaska.domain.useCases.remote.token.InitTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideInitTokenUseCase(
        networkRepository: NetworkRepository,
        serverConfiguration: ServerConfiguration,
    ) = InitTokenUseCase(networkRepository, serverConfiguration)

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(
        networkRepository: NetworkRepository,
        serverConfiguration: ServerConfiguration,
    ) = LoginUseCase(networkRepository, serverConfiguration)

    @Provides
    @ViewModelScoped
    fun provideRecoveryPassword(
        networkRepository: NetworkRepository,
        serverConfiguration: ServerConfiguration,
    ) = RecoveryPasswordUseCase(networkRepository, serverConfiguration)

    @Provides
    @ViewModelScoped
    fun provideRegisterUseCase(
        networkRepository: NetworkRepository,
        serverConfiguration: ServerConfiguration,
    ) = RegisterUseCase(networkRepository, serverConfiguration)

    @Provides
    @ViewModelScoped
    fun provideCreatePostUseCase(
        networkRepository: NetworkRepository,
        serverConfiguration: ServerConfiguration,
    ) = CreatePostUseCase(networkRepository, serverConfiguration)

    @Provides
    @ViewModelScoped
    fun provideGetPostsUseCase(
        networkRepository: NetworkRepository,
        serverConfiguration: ServerConfiguration,
    ) = GetPostUseCase(networkRepository, serverConfiguration)
}