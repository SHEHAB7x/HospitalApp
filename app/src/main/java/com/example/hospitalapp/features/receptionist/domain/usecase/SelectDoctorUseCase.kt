package com.example.hospitalapp.features.receptionist.domain.usecase

import com.example.hospitalapp.features.receptionist.domain.model.AllDoctors
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class SelectDoctorUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(type: String) : ResponseState<List<AllDoctors>>{
        return repository.getAllDoctors(type)
    }
}