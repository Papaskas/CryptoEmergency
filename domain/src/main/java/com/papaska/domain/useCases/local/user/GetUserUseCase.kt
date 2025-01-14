package com.papaska.domain.useCases.local.user

import com.papaska.domain.entity.local.UserEntity
import com.papaska.domain.repositories.local.storage.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): UserEntity {
        return userRepository.get()
    }
}