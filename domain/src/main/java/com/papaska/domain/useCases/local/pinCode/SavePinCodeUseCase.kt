package com.papaska.domain.useCases.local.pinCode

import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.PinCodeRepository
import com.papaska.domain.repositories.local.TokenRepository

class SavePinCodeUseCase(private val pinCodeRepository: PinCodeRepository) {
    suspend operator fun invoke(pinCode: PinCodeEntity) {
        pinCodeRepository.put(pinCode)
    }
}
