package com.example.hospitalapp.features.specialist.domain.usecase

import com.example.hospitalapp.features.specialist.domain.model.ShowCall
import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class ShowCallUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(id : Int) : ResponseState<ShowCall>{
        return repository.showCall(id)
    }
}