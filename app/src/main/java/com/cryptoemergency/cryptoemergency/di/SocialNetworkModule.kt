package com.cryptoemergency.cryptoemergency.di

import com.papaska.core.repositories.local.db.SocialNetworkRepository
import com.papaska.core.useCases.local.socialNetwork.DeleteSocialNetworkItemById
import com.papaska.core.useCases.local.socialNetwork.GetAllSocialNetworksByNameUseCase
import com.papaska.core.useCases.local.socialNetwork.GetAllSocialNetworksUseCase
import com.papaska.core.useCases.local.socialNetwork.InsertSocialNetworkItemUseCase
import com.papaska.core.useCases.local.socialNetwork.UpdateSocialNetworkItemById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SocialNetworkModule {

    @Provides
    @ViewModelScoped
    fun provideDeleteSocialNetworkItemById(
        socialNetworkRepository: SocialNetworkRepository,
    ) = DeleteSocialNetworkItemById(socialNetworkRepository)

    @Provides
    @ViewModelScoped
    fun provideGetAllSocialNetworksUseCase(
        socialNetworkRepository: SocialNetworkRepository,
    ) = GetAllSocialNetworksUseCase(socialNetworkRepository)

    @Provides
    @ViewModelScoped
    fun provideGetAllSocialNetworksByNameUseCase(
        socialNetworkRepository: SocialNetworkRepository,
    ) = GetAllSocialNetworksByNameUseCase(socialNetworkRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertSocialNetworkItemUseCase(
        socialNetworkRepository: SocialNetworkRepository,
    ) = InsertSocialNetworkItemUseCase(socialNetworkRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateSocialNetworkItemById(
        socialNetworkRepository: SocialNetworkRepository,
    ) = UpdateSocialNetworkItemById(socialNetworkRepository)
}