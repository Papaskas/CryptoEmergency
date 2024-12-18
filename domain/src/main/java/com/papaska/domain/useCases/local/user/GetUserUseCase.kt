package com.papaska.domain.useCases.local.user

import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): TokenEntity {
        return userRepository.get()
    }
}