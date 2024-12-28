package com.papaska.core.useCases.local.user

import com.papaska.core.entity.local.UserEntity
import com.papaska.core.repositories.local.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        userRepository.put(user)
    }
}
