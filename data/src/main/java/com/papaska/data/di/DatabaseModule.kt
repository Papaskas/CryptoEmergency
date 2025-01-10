package com.papaska.data.di

import android.content.Context
import com.papaska.core.constants.StringsConstants.DATABASE_NAME
import com.papaska.data.dao.SocialNetworkDao
import com.papaska.data.infrastructure.local.room.AppDatabase
import com.papaska.data.infrastructure.local.room.createDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = createDatabase(
        context,
        AppDatabase::class.java,
        DATABASE_NAME,
    )

    @Provides
    @Singleton
    fun provideSocialNetworkDao(
        appDatabase: AppDatabase
    ): SocialNetworkDao = appDatabase.socialNetworksDao()
}
