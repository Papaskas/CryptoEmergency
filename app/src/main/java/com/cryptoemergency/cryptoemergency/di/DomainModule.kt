package com.cryptoemergency.cryptoemergency.di

import com.papaska.domain.repositories.local.PinCodeRepository
import com.papaska.domain.repositories.local.ThemeRepository
import com.papaska.domain.repositories.local.TokenRepository
import com.papaska.domain.repositories.local.UserRepository
import com.papaska.domain.useCases.local.pinCode.GetPinCodeUseCase
import com.papaska.domain.useCases.local.pinCode.SavePinCodeUseCase
import com.papaska.domain.useCases.local.theme.GetThemeUseCase
import com.papaska.domain.useCases.local.theme.SaveThemeUseCase
import com.papaska.domain.useCases.local.token.GetTokenUseCase
import com.papaska.domain.useCases.local.token.SaveTokenUseCase
import com.papaska.domain.useCases.local.user.GetUserUseCase
import com.papaska.domain.useCases.local.user.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

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