package com.papaska.core.useCases.local.socialNetwork

import com.papaska.core.repositories.local.db.SocialNetworkRepository

class DeleteSocialNetworkItemById(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(id: Int) =
        socialNetworkRepository.delete(id)
}