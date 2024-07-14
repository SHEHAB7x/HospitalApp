package com.example.hospitalapp.features.hr.domain.usecase

import com.example.hospitalapp.features.hr.domain.models.RegisterNewUser
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import com.example.hospitalapp.framework.network.ResponseState

class RegisterNewUserUseCase(private val hrRepository: IHrRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        gender: String,
        specialist: String,
        birthday: String,
        status: String,
        address: String,
        mobile: String,
        type: String
    ): ResponseState<RegisterNewUser> {
        return hrRepository.registerNewUser(
            email,
            password,
            firstName,
            lastName,
            gender,
            specialist,
            birthday,
            status,
            address,
            mobile,
            type
        )
    }
}