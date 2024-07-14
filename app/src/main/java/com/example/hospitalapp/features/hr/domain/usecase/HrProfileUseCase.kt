package com.example.hospitalapp.features.hr.domain.usecase

import com.example.hospitalapp.features.hr.domain.models.UserProfile
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import com.example.hospitalapp.framework.network.ResponseState

class HrProfileUseCase(private val hrRepository: IHrRepository)  {
    suspend operator fun invoke(id : Int) : ResponseState<UserProfile>{
        return hrRepository.getUserProfile(id)
    }
}