package com.papaska.domain.useCases.local.theme

import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.repositories.local.ThemeRepository

class GetThemeUseCase(private val themeRepository: ThemeRepository) {
    suspend operator fun invoke(): ThemeEntity {
        return themeRepository.get()
    }
}