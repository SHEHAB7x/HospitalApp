package com.example.hospitalapp.features.login.domain.usecase

import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.features.login.domain.repository.ILoginRepository
import com.example.hospitalapp.framework.network.ResponseState

class LoginUseCase(private val repository: ILoginRepository) {
    suspend operator fun invoke(email: String, password:String,deviceToken:String): ResponseState<User>{
        return repository.login(email,password,deviceToken)
    }
}