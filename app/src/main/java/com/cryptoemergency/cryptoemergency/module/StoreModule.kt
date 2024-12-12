package com.cryptoemergency.cryptoemergency.module

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.store.DataStore
import com.cryptoemergency.cryptoemergency.api.domain.model.store.Keys
import com.cryptoemergency.cryptoemergency.api.domain.repository.StorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {
    @TokenStore
    @Provides
    @Singleton
    fun provideStoreToken(
        @ApplicationContext context: Context,
    ): StorageRepository<String> = DataStore(Keys.TOKEN, context)

    @PinCodeStore
    @Provides
    @Singleton
    fun providePinCode(
        @ApplicationContext context: Context,
    ): StorageRepository<String> = DataStore(Keys.PinCode, context)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PinCodeStore
