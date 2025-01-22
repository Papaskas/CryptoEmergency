package com.papaska.domain.useCases.storage.theme

import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.repositories.local.storage.ThemeRepository

class SaveThemeUseCase(private val themeRepository: ThemeRepository) {
    suspend operator fun invoke(user: ThemeEntity) {
        themeRepository.put(user)
    }
}
