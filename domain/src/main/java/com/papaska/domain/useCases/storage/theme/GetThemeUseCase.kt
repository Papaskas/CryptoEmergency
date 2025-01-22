package com.papaska.domain.useCases.storage.theme

import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.repositories.local.storage.ThemeRepository

class GetThemeUseCase(private val themeRepository: ThemeRepository) {
    suspend operator fun invoke(): ThemeEntity {
        return themeRepository.get()
    }
}