package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.model.LogoutCall
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState

class LogoutCallUseCase(private val repository: IDoctorRepository) {
    suspend operator fun invoke(id : Int) : ResponseState<LogoutCall> {
        return repository.logoutCall(id)
    }
}