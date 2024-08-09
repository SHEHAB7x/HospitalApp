package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository

class AcceptRejectUseCase(private val repository: IDoctorRepository) {
    suspend operator fun invoke(id : Int, status: String){
        repository.acceptRejectCall(id,status)
    }
}