package com.papaska.core.useCases.local.socialNetwork

import com.papaska.core.repositories.local.db.SocialNetworkRepository

class GetAllSocialNetworksUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    operator fun invoke() = socialNetworkRepository.getAll()
}