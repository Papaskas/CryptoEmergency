package com.papaska.domain.useCases.db.socialNetwork

import com.papaska.domain.repositories.local.db.SocialNetworkRepository

class GetAllSocialNetworksUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke() = socialNetworkRepository.getAll()
}