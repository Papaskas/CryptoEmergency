package com.papaska.data.dataSources.local.pinCode

import com.papaska.domain.entity.local.PinCodeEntity

interface PinCodeDataSource {
    suspend fun read(): PinCodeEntity
    suspend fun createOrUpdate(pinCode: PinCodeEntity)
}