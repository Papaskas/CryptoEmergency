package com.papaska.data.dataSources.local.pinCode

import com.papaska.data.Infrastructure.local.datastore.DataStore
import com.papaska.domain.entity.local.PinCodeEntity

class PinCodeDataSourceImpl(
    private val protoDataStore: DataStore<PinCodeEntity>
) : PinCodeDataSource {
    override suspend fun read(): PinCodeEntity = protoDataStore.get()
    override suspend fun createOrUpdate(pinCode: PinCodeEntity) = protoDataStore.put(pinCode)
}