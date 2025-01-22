package com.papaska.domain.useCases.storage.pinCode

import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.repositories.local.storage.PinCodeRepository

class SavePinCodeUseCase(private val pinCodeRepository: PinCodeRepository) {
    suspend operator fun invoke(pinCode: PinCodeEntity) {
        pinCodeRepository.put(pinCode)
    }
}
