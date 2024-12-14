package com.cryptoemergency.cryptoemergency.module

import android.content.Context
import androidx.room.Room
import com.cryptoemergency.cryptoemergency.api.data.database.AppDatabase
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
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase").build()
}
