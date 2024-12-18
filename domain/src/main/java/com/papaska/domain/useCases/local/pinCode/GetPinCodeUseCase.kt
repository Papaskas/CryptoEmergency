package com.papaska.domain.useCases.local.pinCode

import com.papaska.domain.entity.local.PinCodeEntity
import com.papaska.domain.repositories.local.PinCodeRepository

class GetPinCodeUseCase(private val pinCodeRepository: PinCodeRepository) {
    suspend operator fun invoke(): PinCodeEntity {
        return pinCodeRepository.get()
    }
}