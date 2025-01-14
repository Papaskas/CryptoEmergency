package com.papaska.domain.entity.local

import kotlinx.serialization.Serializable

/**
 * Набор тем приложения
 * */
@Serializable
enum class ThemeEntity {
    DARK,
    LIGHT,

    /**
     * Тема не установлена вручную
     * */
    AUTO,
}