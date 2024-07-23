package com.example.hospitalapp.features.receptionist.domain.usecase

import com.example.hospitalapp.features.receptionist.domain.model.CreateCall
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class CreateCallUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(
        patientName: String,
        doctorId: Int,
        age: String,
        phone: String,
        description: String
    ): ResponseState<CreateCall>{
        return repository.createCall(patientName,doctorId, age, phone, description)
    }
}