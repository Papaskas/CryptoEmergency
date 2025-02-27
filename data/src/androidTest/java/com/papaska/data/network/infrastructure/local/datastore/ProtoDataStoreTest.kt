package com.papaska.data.network.infrastructure.local.datastore

import androidx.test.platform.app.InstrumentationRegistry
import com.papaska.data.network.infrastructure.local.datastore.keys.ProtoKeyImpl
import com.papaska.data.repositories.local.ProtoLocalStorageRepositoryImpl
import com.papaska.domain.entity.local.ThemeEntity
import com.papaska.domain.repositories.local.storage.LocalStorageRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class ProtoDataStoreTest {

    companion object{
        private lateinit var localStorageRepository: LocalStorageRepository<ThemeEntity>

        @JvmStatic
        @BeforeClass
        fun init() {
            localStorageRepository  = ProtoLocalStorageRepositoryImpl(
                context = InstrumentationRegistry.getInstrumentation().targetContext,
                key = ProtoKeyImpl.THEME,
            )
        }
    }

    @Before
    fun clearValue() = runTest {
        localStorageRepository.clear()
    }

    @Test
    fun saveEqualReadValue() = runTest {
        val valueForSave = ThemeEntity.DARK

        localStorageRepository.put(valueForSave)

        val storageValue = localStorageRepository.get()

        assertEquals(valueForSave, storageValue)
    }

    @Test
    fun isDefaultValue() = runTest {
        val storageValue = localStorageRepository.get()

        assertEquals(storageValue, ProtoKeyImpl.THEME.value)
        assertNotEquals(storageValue, ThemeEntity.DARK)
    }

    @Test
    fun clearIsWorking() = runTest {
        val testValue = ThemeEntity.LIGHT

        localStorageRepository.put(testValue)
        localStorageRepository.clear()
        val storageValue = localStorageRepository.get()

        assertNotEquals(storageValue, testValue)
        assertEquals(storageValue, ProtoKeyImpl.THEME.value)
    }
}