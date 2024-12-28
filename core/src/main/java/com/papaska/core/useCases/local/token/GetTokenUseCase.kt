package com.papaska.core.useCases.local.token

import com.papaska.core.repositories.local.TokenRepository

class GetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke() =
         tokenRepository.get()
}