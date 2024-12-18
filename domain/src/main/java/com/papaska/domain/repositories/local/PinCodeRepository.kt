package com.papaska.domain.repositories.local

import com.papaska.domain.entity.local.PinCodeEntity

interface PinCodeRepository {
    suspend fun get(): PinCodeEntity
    suspend fun put(pinCode: PinCodeEntity)
}
