package com.papaska.core.useCases.local.theme

import com.papaska.core.entity.local.ThemeEntity
import com.papaska.core.repositories.local.ThemeRepository

class GetThemeUseCase(private val themeRepository: ThemeRepository) {
    suspend operator fun invoke(): ThemeEntity {
        return themeRepository.get()
    }
}