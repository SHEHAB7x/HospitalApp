package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.model.AllCases
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState

class AllCasesUseCase(private val repository: IDoctorRepository) {
    suspend operator fun invoke(): ResponseState<List<AllCases>> {
        return repository.getAllCases()
    }
}