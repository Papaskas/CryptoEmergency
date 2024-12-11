package com.cryptoemergency.cryptoemergency.api.domain.model.store.data

import kotlinx.serialization.Serializable

@Serializable
enum class CurrentTheme {
    DARK,
    LIGHT,
    NULL,
}
