package com.cryptoemergency.cryptoemergency.module

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.data.store.ProtoDataStoreImpl
import com.cryptoemergency.cryptoemergency.api.domain.model.store.ProtoKeys
import com.cryptoemergency.cryptoemergency.api.domain.model.store.data.CurrentTheme
import com.cryptoemergency.cryptoemergency.api.domain.model.store.data.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProtoStoreModule {
    @Provides
    @Singleton
    fun provideUserProtoStore(
        @ApplicationContext context: Context,
    ): ProtoDataStoreImpl<User> = ProtoDataStoreImpl(ProtoKeys.USER, context)

    @Provides
    @Singleton
    fun provideThemeProtoStore(
        @ApplicationContext context: Context,
    ): ProtoDataStoreImpl<CurrentTheme> = ProtoDataStoreImpl(ProtoKeys.THEME, context)
}
