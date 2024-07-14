package com.example.hospitalapp.features.login.domain.repository
import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.framework.network.ResponseState

interface ILoginRepository {
    suspend fun login(
        email: String,
        password: String,
        deviceToken: String
    ): ResponseState<User>
}