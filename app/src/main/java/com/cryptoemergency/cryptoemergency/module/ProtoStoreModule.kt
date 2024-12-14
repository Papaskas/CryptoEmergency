package com.cryptoemergency.cryptoemergency.module

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.store.ProtoDataStore
import com.cryptoemergency.cryptoemergency.api.domain.model.store.ProtoKeys
import com.cryptoemergency.cryptoemergency.api.domain.model.store.data.CurrentTheme
import com.cryptoemergency.cryptoemergency.api.domain.model.store.data.User
import com.cryptoemergency.cryptoemergency.api.domain.repository.StorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль для предоставления зависимостей, связанных с хранилищем данных.
 *
 * Этот модуль используется Hilt для внедрения зависимостей в компоненты приложения.
 **/
@Module
@InstallIn(SingletonComponent::class)
object ProtoStoreModule {
    /**
     * Предоставляет экземпляр [StorageRepository] для хранения данных о пользователе.
     *
     * @param context [Context] Контекст приложения.
     * @return [StorageRepository] Экземпляр хранилища для данных пользователя.
     */
    @Provides
    @Singleton
    fun provideUserProtoStore(
        @ApplicationContext context: Context,
    ): StorageRepository<User> = ProtoDataStore(ProtoKeys.USER, context)

    /**
     * Предоставляет экземпляр [StorageRepository] для хранения текущей темы.
     *
     * @param context [Context] Контекст приложения.
     * @return [StorageRepository] Экземпляр хранилища для текущей темы.
     */
    @Provides
    @Singleton
    fun provideThemeProtoStore(
        @ApplicationContext context: Context,
    ): StorageRepository<CurrentTheme> = ProtoDataStore(ProtoKeys.THEME, context)
}
