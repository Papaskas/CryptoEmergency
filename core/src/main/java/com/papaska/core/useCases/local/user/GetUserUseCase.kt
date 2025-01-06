package com.papaska.core.useCases.local.user

import com.papaska.core.entity.local.UserEntity
import com.papaska.core.repositories.local.storage.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): UserEntity {
        return userRepository.get()
    }
}