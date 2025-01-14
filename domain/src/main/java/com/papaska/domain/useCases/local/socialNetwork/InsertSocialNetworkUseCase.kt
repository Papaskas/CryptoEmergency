package com.papaska.domain.useCases.local.socialNetwork

import com.papaska.domain.entity.socialNetwork.SocialNetworkEntity
import com.papaska.domain.entity.socialNetwork.SocialNetworkName
import com.papaska.domain.repositories.local.db.SocialNetworkRepository

class InsertSocialNetworkUseCase(
    private val socialNetworkRepository: SocialNetworkRepository
) {
    suspend operator fun invoke(socialNetworkEntity: SocialNetworkEntity) {
        require(socialNetworkEntity.socialNetworkName != SocialNetworkName.NONE)

        return socialNetworkRepository.insert(socialNetworkEntity)
    }
}