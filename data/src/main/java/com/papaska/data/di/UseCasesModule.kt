package com.papaska.data.di

import com.papaska.domain.repositories.local.storage.PinCodeRepository
import com.papaska.domain.repositories.local.storage.ThemeRepository
import com.papaska.domain.repositories.local.storage.TokenRepository
import com.papaska.domain.repositories.local.storage.UserRepository
import com.papaska.domain.useCases.storage.pinCode.GetPinCodeUseCase
import com.papaska.domain.useCases.storage.pinCode.SavePinCodeUseCase
import com.papaska.domain.useCases.storage.theme.GetThemeUseCase
import com.papaska.domain.useCases.storage.theme.SaveThemeUseCase
import com.papaska.domain.useCases.storage.token.GetTokenUseCase
import com.papaska.domain.useCases.storage.token.SaveTokenUseCase
import com.papaska.domain.useCases.storage.user.GetUserUseCase
import com.papaska.domain.useCases.storage.user.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ) = GetUserUseCase(userRepository = userRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveUserUseCase(
        userRepository: UserRepository
    ) = SaveUserUseCase(userRepository = userRepository)

    @Provides
    @ViewModelScoped
    fun provideGetThemeUseCase(
        themeRepository: ThemeRepository
    ) = GetThemeUseCase(themeRepository = themeRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveThemeUseCase(
        themeRepository: ThemeRepository
    ) = SaveThemeUseCase(themeRepository = themeRepository)

    @Provides
    @ViewModelScoped
    fun provideGetTokenUseCase(
        tokenRepository: TokenRepository
    ) = GetTokenUseCase(tokenRepository = tokenRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveTokenUseCase(
        tokenRepository: TokenRepository
    ) = SaveTokenUseCase(tokenRepository = tokenRepository)

    @Provides
    @ViewModelScoped
    fun provideGetPinCodeUseCase(
        pinCodeRepository: PinCodeRepository
    ) = GetPinCodeUseCase(pinCodeRepository = pinCodeRepository)

    @Provides
    @ViewModelScoped
    fun provideSavePinCodeUseCase(
        pinCodeRepository: PinCodeRepository
    ) = SavePinCodeUseCase(pinCodeRepository = pinCodeRepository)
}