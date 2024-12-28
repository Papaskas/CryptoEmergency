package com.papaska.core.entity.local

import kotlinx.serialization.Serializable

/**
 * Набор тем приложения
 * */
@Serializable
enum class ThemeEntity {
    DARK,
    LIGHT,
    /**
     * Тема не установленна вручную
     * */
    NULL,
}