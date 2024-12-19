package com.papaska.data.repositories.local

import com.papaska.data.dataSources.local.localStorage.LocalStorageDataSource
import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.repositories.local.PinCodeRepository

class PinCodeRepositoryImpl(private val pinCodeDataSource: LocalStorageDataSource<PinCodeEntity>) : PinCodeRepository {
    override suspend fun get(): PinCodeEntity = pinCodeDataSource.read()
    override suspend fun put(pinCode: PinCodeEntity) = pinCodeDataSource.createOrUpdate(pinCode)
}
