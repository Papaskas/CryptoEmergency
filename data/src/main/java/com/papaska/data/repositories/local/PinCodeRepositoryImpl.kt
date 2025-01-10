package com.papaska.data.repositories.local

import com.papaska.data.dataSources.localStorage.LocalStorageDataSource
import com.papaska.core.entity.local.PinCodeEntity
import com.papaska.core.repositories.local.storage.PinCodeRepository

class PinCodeRepositoryImpl(
    private val pinCodeDataSource: LocalStorageDataSource<PinCodeEntity>
) : PinCodeRepository {
    override suspend fun get(): PinCodeEntity = pinCodeDataSource.read()
    override suspend fun put(pinCode: PinCodeEntity) = pinCodeDataSource.createOrUpdate(pinCode)
}
