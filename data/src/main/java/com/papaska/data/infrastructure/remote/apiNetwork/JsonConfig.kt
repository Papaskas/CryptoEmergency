package com.papaska.data.infrastructure.remote.apiNetwork

import com.papaska.domain.constants.HttpConstants.JSON_IGNORE_UNKNOWN_KEYS
import com.papaska.domain.constants.HttpConstants.JSON_IS_LENIENT
import kotlinx.serialization.json.Json

/**
 * Конфигуратор JSON
 */
internal val jsonConfig by lazy {
    Json {
        isLenient = JSON_IS_LENIENT
        ignoreUnknownKeys = JSON_IGNORE_UNKNOWN_KEYS
    }
}
