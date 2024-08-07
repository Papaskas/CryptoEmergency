package com.cryptoemergency.cryptoemergency.module

import android.content.Context
import com.cryptoemergency.cryptoemergency.api.store.ProtoStore
import com.cryptoemergency.cryptoemergency.repository.store.ProtoKeys
import com.cryptoemergency.cryptoemergency.repository.store.data.CurrentTheme
import com.cryptoemergency.cryptoemergency.repository.store.data.User
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
    fun provideUserProtoStore(@ApplicationContext context: Context): ProtoStore<User> {
        return ProtoStore(ProtoKeys.USER, context)
    }

    @Provides
    @Singleton
    fun provideThemeProtoStore(@ApplicationContext context: Context): ProtoStore<CurrentTheme> {
        return ProtoStore(ProtoKeys.THEME, context)
    }
}
