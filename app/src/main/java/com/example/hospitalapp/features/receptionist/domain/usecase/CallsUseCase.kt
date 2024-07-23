package com.example.hospitalapp.features.receptionist.domain.usecase

import com.example.hospitalapp.features.receptionist.domain.model.AllCalls
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class CallsUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(date : String) : ResponseState<List<AllCalls>>{
        return repository.getAllCalls(date)
    }
}