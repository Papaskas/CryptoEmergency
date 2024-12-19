package com.papaska.domain.di

import com.papaska.domain.repositories.local.PinCodeRepository
import com.papaska.domain.repositories.local.ThemeRepository
import com.papaska.domain.repositories.local.TokenRepository
import com.papaska.domain.repositories.local.UserRepository
import com.papaska.domain.useCases.local.pinCode.GetPinCodeUseCase
import com.papaska.domain.useCases.local.pinCode.SavePinCodeUseCase
import com.papaska.domain.useCases.local.theme.GetThemeUseCase
import com.papaska.domain.useCases.local.token.GetTokenUseCase
import com.papaska.domain.useCases.local.token.SaveTokenUseCase
import com.papaska.domain.useCases.local.user.GetUserUseCase
import com.papaska.domain.useCases.local.user.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ): GetUserUseCase = GetUserUseCase(userRepository = userRepository)

    @Provides
    fun provideSaveUserUseCase(
        userRepository: UserRepository
    ): SaveUserUseCase = SaveUserUseCase(userRepository = userRepository)

    @Provides
    fun provideGetThemeUseCase(
        themeRepository: ThemeRepository
    ): GetThemeUseCase = GetThemeUseCase(themeRepository = themeRepository)

    @Provides
    fun provideSaveThemeUseCase(
        themeRepository: ThemeRepository
    ): GetThemeUseCase = GetThemeUseCase(themeRepository = themeRepository)

    @Provides
    fun provideGetTokenUseCase(
        tokenRepository: TokenRepository
    ): GetTokenUseCase = GetTokenUseCase(tokenRepository = tokenRepository)

    @Provides
    fun provideSaveTokenUseCase(
        tokenRepository: TokenRepository
    ): SaveTokenUseCase = SaveTokenUseCase(tokenRepository = tokenRepository)

    @Provides
    fun provideGetPinCodeUseCase(
        pinCodeRepository: PinCodeRepository
    ): GetPinCodeUseCase = GetPinCodeUseCase(pinCodeRepository = pinCodeRepository)

    @Provides
    fun provideSavePinCodeUseCase(
        pinCodeRepository: PinCodeRepository
    ): SavePinCodeUseCase = SavePinCodeUseCase(pinCodeRepository = pinCodeRepository)
}