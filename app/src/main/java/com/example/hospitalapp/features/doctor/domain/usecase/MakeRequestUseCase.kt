package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState

class MakeRequestUseCase(private val repository: IDoctorRepository) {
    suspend operator fun invoke(caseId : Int, userId: Int, note: String, request: List<String>) : ResponseState<Int> {
        return repository.makeRequest(caseId,userId,note,request)
    }
}