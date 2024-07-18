package com.example.hospitalapp.features.hr.data.datasource

import com.example.hospitalapp.features.hr.data.models.ModelAllUsers
import com.example.hospitalapp.features.hr.data.models.ModelProfileUser
import com.example.hospitalapp.features.hr.data.models.ModelRegisterNewUser
import com.example.hospitalapp.features.login.data.datasource.local.LoginLocalDataSource
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.framework.network.RetrofitService
import javax.inject.Inject

class HrRemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) : IHrRemoteDataSource {

    override suspend fun getUserProfile(id: Int): ResponseState<ModelProfileUser> {
        val response = retrofitService.showProfile(id)
        return if (response.status == 1) {
            ResponseState.Success(response)
        } else {
            ResponseState.Error(response.message)
        }
    }

    override suspend fun getAllUsers(type :String) : ResponseState<ModelAllUsers>{
        val response = retrofitService.getUserByType(type)
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun registerNewUser(
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
    ): ResponseState<ModelRegisterNewUser> {
        val response = retrofitService.registerUser(
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

        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

}