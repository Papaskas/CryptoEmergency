package com.papaska.core.useCases.local.token

import com.papaska.core.entity.local.TokenEntity
import com.papaska.core.repositories.local.storage.TokenRepository

class SaveTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(token: TokenEntity) {
        tokenRepository.put(token)
    }
}
