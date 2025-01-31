package com.papaska.data.qualifiers

import javax.inject.Qualifier

object StorageQualifiers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TokenStorage

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TokenStorageRepository

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PinCodeStorage

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PinCodeStorageRepository
}
