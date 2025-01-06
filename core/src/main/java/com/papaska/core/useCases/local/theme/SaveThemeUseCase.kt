package com.papaska.core.useCases.local.theme

import com.papaska.core.entity.local.ThemeEntity
import com.papaska.core.repositories.local.storage.ThemeRepository

class SaveThemeUseCase(private val themeRepository: ThemeRepository) {
    suspend operator fun invoke(user: ThemeEntity) {
        themeRepository.put(user)
    }
}
