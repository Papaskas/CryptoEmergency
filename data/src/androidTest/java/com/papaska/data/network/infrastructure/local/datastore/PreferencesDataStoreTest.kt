package com.papaska.data.network.infrastructure.local.datastore

import androidx.test.platform.app.InstrumentationRegistry
import com.papaska.data.network.infrastructure.local.datastore.keys.PreferencesKey
import com.papaska.data.repositories.local.PreferencesLocalStorageRepositoryImpl
import com.papaska.domain.repositories.local.storage.LocalStorageRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class PreferencesDataStoreTest {

    companion object{
        private lateinit var localStorageRepository: LocalStorageRepository<String>

        @JvmStatic
        @BeforeClass
        fun init() {
            localStorageRepository  = PreferencesLocalStorageRepositoryImpl(
                context = InstrumentationRegistry.getInstrumentation().targetContext,
                key = PreferencesKey.SampleString,
            )
        }
    }

    @Before
    fun clearValue() = runTest {
        localStorageRepository.clear()
    }

    @Test
    fun saveEqualReadValue() = runTest {
        val valueForSave = "Test string value"

        localStorageRepository.put(valueForSave)

        val storageValue = localStorageRepository.get()

        assertEquals(valueForSave, storageValue)
    }

    @Test
    fun isDefaultValue() = runTest {
        val storageValue = localStorageRepository.get()

        assertEquals(storageValue, PreferencesKey.SampleString.defaultValue)
        assertNotEquals(storageValue, "Test string value")
    }

    @Test
    fun clearIsWorking() = runTest {
        val testValue = "test value"

        localStorageRepository.put(testValue)
        localStorageRepository.clear()
        val storageValue = localStorageRepository.get()

        assertNotEquals(storageValue, testValue)
        assertEquals(storageValue, PreferencesKey.SampleString.defaultValue)
    }
}
