package com.papaska.data.di

import com.papaska.domain.repositories.local.db.SocialNetworkRepository
import com.papaska.domain.useCases.db.socialNetwork.DeleteSocialNetworkItemById
import com.papaska.domain.useCases.db.socialNetwork.GetAllSocialNetworksByNameUseCase
import com.papaska.domain.useCases.db.socialNetwork.GetAllSocialNetworksUseCase
import com.papaska.domain.useCases.db.socialNetwork.InsertSocialNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SocialNetworkUseCasesModule {

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
    ) = InsertSocialNetworkUseCase(socialNetworkRepository)
}