package com.example.hospitalapp.features.hr.domain.usecase

import com.example.hospitalapp.features.hr.domain.models.User
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class AllUsersByTypeUseCase @Inject constructor(private val hrRepository: IHrRepository) {
    suspend operator fun invoke(type : String) : ResponseState<List<User>>{
        return hrRepository.getAllEmployee(type)
    }
}