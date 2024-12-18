package com.papaska.domain.useCases.local.user

import com.papaska.domain.entity.local.UserEntity
import com.papaska.domain.repositories.local.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        userRepository.put(user)
    }
}
