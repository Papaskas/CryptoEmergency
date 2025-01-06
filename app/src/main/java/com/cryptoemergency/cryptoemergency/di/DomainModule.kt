package com.cryptoemergency.cryptoemergency.di

import com.papaska.core.repositories.local.storage.PinCodeRepository
import com.papaska.core.repositories.local.storage.ThemeRepository
import com.papaska.core.repositories.local.storage.TokenRepository
import com.papaska.core.repositories.local.storage.UserRepository
import com.papaska.core.useCases.local.pinCode.GetPinCodeUseCase
import com.papaska.core.useCases.local.pinCode.SavePinCodeUseCase
import com.papaska.core.useCases.local.theme.GetThemeUseCase
import com.papaska.core.useCases.local.theme.SaveThemeUseCase
import com.papaska.core.useCases.local.token.GetTokenUseCase
import com.papaska.core.useCases.local.token.SaveTokenUseCase
import com.papaska.core.useCases.local.user.GetUserUseCase
import com.papaska.core.useCases.local.user.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

