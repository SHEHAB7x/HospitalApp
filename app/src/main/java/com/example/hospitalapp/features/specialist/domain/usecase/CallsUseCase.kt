package com.example.hospitalapp.features.specialist.domain.usecase

import com.example.hospitalapp.features.specialist.domain.model.AllCalls
import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class CallsUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(date : String) : ResponseState<List<AllCalls>>{
        return repository.getAllCalls(date)
    }
}