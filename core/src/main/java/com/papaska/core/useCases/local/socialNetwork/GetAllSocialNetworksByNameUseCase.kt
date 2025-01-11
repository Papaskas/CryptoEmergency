package com.papaska.core.useCases.local.socialNetwork

import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.core.repositories.local.db.SocialNetworkRepository

class GetAllSocialNetworksByNameUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(socialNetworkName: SocialNetworkName) =
        socialNetworkRepository.getAllBySocialNetworkName(socialNetworkName)
}
