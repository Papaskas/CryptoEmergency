package com.papaska.core.useCases.local.socialNetwork

import com.papaska.core.repositories.local.db.SocialNetworkRepository

class UpdateSocialNetworkItemById(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(id: Int, url: String, description: String) =
        socialNetworkRepository.updateItemById(id, url, description)
}