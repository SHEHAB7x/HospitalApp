package com.example.hospitalapp.features.hr.domain.repo
import com.example.hospitalapp.features.hr.domain.models.RegisterNewUser
import com.example.hospitalapp.features.hr.domain.models.User
import com.example.hospitalapp.features.hr.domain.models.UserProfile
import com.example.hospitalapp.framework.network.ResponseState

interface IHrRepository {
    suspend fun getUserProfile(id: Int): ResponseState<UserProfile>
    suspend fun getAllEmployee(type: String): ResponseState<List<User>>
    suspend fun registerNewUser(
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
    ): ResponseState<RegisterNewUser>

}