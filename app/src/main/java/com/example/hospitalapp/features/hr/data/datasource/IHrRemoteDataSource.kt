package com.example.hospitalapp.features.hr.data.datasource

import com.example.hospitalapp.features.hr.data.models.ModelAllUsers
import com.example.hospitalapp.features.hr.data.models.ModelProfileUser
import com.example.hospitalapp.features.hr.data.models.ModelRegisterNewUser
import com.example.hospitalapp.framework.network.ResponseState

interface IHrRemoteDataSource {
    suspend fun getUserProfile(id: Int): ResponseState<ModelProfileUser>
    suspend fun getAllUsers(type :String) : ResponseState<ModelAllUsers>
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
    ): ResponseState<ModelRegisterNewUser>
}