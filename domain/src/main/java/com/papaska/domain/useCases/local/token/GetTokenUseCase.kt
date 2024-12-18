package com.papaska.domain.useCases.local.token

import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.TokenRepository

class GetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(): TokenEntity {
        return tokenRepository.get()
    }
}