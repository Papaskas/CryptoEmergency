package com.papaska.core.useCases.local.pinCode

import com.papaska.core.entity.local.PinCodeEntity
import com.papaska.core.repositories.local.storage.PinCodeRepository

class SavePinCodeUseCase(private val pinCodeRepository: PinCodeRepository) {
    suspend operator fun invoke(pinCode: PinCodeEntity) {
        pinCodeRepository.put(pinCode)
    }
}
