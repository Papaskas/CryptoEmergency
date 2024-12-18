package com.cryptoemergency.cryptoemergency.di.old

//import android.content.Context
//import com.cryptoemergency.cryptoemergency.api.data.store.DataStore
//import com.cryptoemergency.cryptoemergency.api.domain.model.store.Keys
//import com.cryptoemergency.cryptoemergency.api.domain.repository.StorageRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Qualifier
//import javax.inject.Singleton
//
///**
// * Модуль для предоставления зависимостей, связанных с хранилищем данных.
// *
// * Этот модуль используется Hilt для внедрения зависимостей в компоненты приложения.
// **/
//@Module
//@InstallIn(SingletonComponent::class)
//object StoreModule {
//    /**
//     * Предоставляет экземпляр [StorageRepository] для хранения токена.
//     *
//     * @param context [Context] Контекст приложения.
//     * @return [StorageRepository] Экземпляр хранилища для токена.
//     */
//    @TokenStore
//    @Provides
//    @Singleton
//    fun provideStoreToken(
//        @ApplicationContext context: Context,
//    ): StorageRepository<String> = DataStore(Keys.TOKEN, context)
//
//    /**
//     * Предоставляет экземпляр [StorageRepository] для хранения PIN-кода.
//     *
//     * @param context [Context] Контекст приложения.
//     * @return [StorageRepository] Экземпляр хранилища для PIN-кода.
//     */
//    @PinCodeStore
//    @Provides
//    @Singleton
//    fun providePinCode(
//        @ApplicationContext context: Context,
//    ): StorageRepository<String> = DataStore(Keys.PinCode, context)
//}
//
///**
// * Аннотация для квалификации зависимости, связанной с токеном.
// *
// * Используется для различения зависимостей в Hilt.
// */
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class TokenStore
//
///**
// * Аннотация для квалификации зависимости, связанной с PIN-кодом.
// *
// * Используется для различения зависимостей в Hilt.
// */
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class PinCodeStore
