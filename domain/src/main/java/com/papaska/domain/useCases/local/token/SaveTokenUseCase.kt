package com.papaska.domain.useCases.local.token

import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.storage.TokenRepository

class SaveTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(token: TokenEntity) {
        tokenRepository.put(token)
    }
}
