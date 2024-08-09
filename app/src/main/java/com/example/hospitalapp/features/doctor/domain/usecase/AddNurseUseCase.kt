package com.example.hospitalapp.features.doctor.domain.usecase

import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState

class AddNurseUseCase (private val repository: IDoctorRepository){
    suspend operator fun invoke(caseId : Int, nurseId: Int) : ResponseState<Int>{
        return repository.addNurse(caseId,nurseId)
    }
}