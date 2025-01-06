package com.papaska.core.useCases.local.pinCode

import com.papaska.core.entity.local.PinCodeEntity
import com.papaska.core.repositories.local.storage.PinCodeRepository

class GetPinCodeUseCase(private val pinCodeRepository: PinCodeRepository) {
    suspend operator fun invoke(): PinCodeEntity {
        return pinCodeRepository.get()
    }
}