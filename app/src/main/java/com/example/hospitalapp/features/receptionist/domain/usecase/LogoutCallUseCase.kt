package com.example.hospitalapp.features.receptionist.domain.usecase

import com.example.hospitalapp.features.receptionist.domain.model.LogoutCall
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class LogoutCallUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(id : Int) : ResponseState<LogoutCall>{
        return repository.logoutCall(id)
    }
}