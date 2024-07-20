package com.example.hospitalapp.features.specialist.domain.usecase

import com.example.hospitalapp.features.specialist.domain.model.AllDoctors
import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class SelectDoctorUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(type: String) : ResponseState<List<AllDoctors>>{
        return repository.getAllDoctors(type)
    }
}