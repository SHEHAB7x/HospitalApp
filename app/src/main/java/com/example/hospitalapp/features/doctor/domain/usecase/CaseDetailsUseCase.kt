package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState

class CaseDetailsUseCase(private val repository: IDoctorRepository) {
    suspend operator fun invoke(id : Int) : ResponseState<CaseDetails>{
        return repository.getCaseDetails(id)
    }
}