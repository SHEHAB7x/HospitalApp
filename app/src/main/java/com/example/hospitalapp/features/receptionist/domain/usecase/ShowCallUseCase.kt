package com.example.hospitalapp.features.receptionist.domain.usecase

import com.example.hospitalapp.features.receptionist.domain.model.ShowCall
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState

class ShowCallUseCase(private val repository: IReceptionistRepository) {
    suspend operator fun invoke(id : Int) : ResponseState<ShowCall>{
        return repository.showCall(id)
    }
}