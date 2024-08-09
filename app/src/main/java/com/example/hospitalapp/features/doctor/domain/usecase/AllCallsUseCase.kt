package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState

class AllCallsUseCase(private val repository: IDoctorRepository) {
    suspend operator fun invoke() : ResponseState<List<AllCallsOfDoctor>>{
        return repository.getAllCallsOfDoctor()
    }
}