package com.papaska.core.repositories.local

import com.papaska.core.entity.local.PinCodeEntity

interface PinCodeRepository {
    suspend fun get(): PinCodeEntity
    suspend fun put(pinCode: PinCodeEntity)
}
