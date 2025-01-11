package com.papaska.core.useCases.local.socialNetwork

import com.papaska.core.entity.db.SocialNetworkEntity
import com.papaska.core.entity.db.SocialNetworkName
import com.papaska.core.repositories.local.db.SocialNetworkRepository

class InsertSocialNetworkUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(socialNetworkEntity: SocialNetworkEntity) {
        require(socialNetworkEntity.socialNetworkName != SocialNetworkName.NONE)

        return socialNetworkRepository.insert(socialNetworkEntity)
    }
}