package com.papaska.data.repositories.local

import com.papaska.data.dataSources.local.pinCode.PinCodeDataSource
import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.repositories.local.PinCodeRepository

class PinCodeRepositoryImpl(private val pinCodeDataSource: PinCodeDataSource) : PinCodeRepository {
    override suspend fun get(): PinCodeEntity = pinCodeDataSource.read()
    override suspend fun put(pinCode: PinCodeEntity) = pinCodeDataSource.createOrUpdate(pinCode)
}
