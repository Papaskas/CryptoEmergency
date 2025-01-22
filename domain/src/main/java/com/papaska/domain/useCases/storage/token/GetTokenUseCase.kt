package com.papaska.domain.useCases.storage.token

import com.papaska.domain.repositories.local.storage.TokenRepository

class GetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke() =
         tokenRepository.get()
}