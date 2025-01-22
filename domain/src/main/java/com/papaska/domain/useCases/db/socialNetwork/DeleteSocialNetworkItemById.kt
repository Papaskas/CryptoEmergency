package com.papaska.domain.useCases.db.socialNetwork

import com.papaska.domain.repositories.local.db.SocialNetworkRepository

class DeleteSocialNetworkItemById(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(id: Int) =
        socialNetworkRepository.delete(id)
}