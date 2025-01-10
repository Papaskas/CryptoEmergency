package com.papaska.core.useCases.local.socialNetwork

import com.papaska.core.entity.db.SocialNetworkItemEntity
import com.papaska.core.repositories.local.db.SocialNetworkRepository

class InsertSocialNetworkItemUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(socialNetworkItem: SocialNetworkItemEntity) =
        socialNetworkRepository.insertItem(socialNetworkItem)
}