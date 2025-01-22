package com.papaska.domain.useCases.db.socialNetwork

import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import com.papaska.domain.repositories.local.db.SocialNetworkRepository

class GetAllSocialNetworksByNameUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(socialNetworkName: SocialNetworkName) =
        socialNetworkRepository.getAllBySocialNetworkName(socialNetworkName)
}
