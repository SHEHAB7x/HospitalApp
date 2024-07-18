package com.example.hospitalapp.features.login.data.datasource.remote

import com.example.hospitalapp.features.login.data.model.ModelUser
import com.example.hospitalapp.framework.network.ResponseState

interface ILoginRemoteDataSource {
    suspend fun login(email:String, password:String, deviceToken:String) : ResponseState<ModelUser>
}