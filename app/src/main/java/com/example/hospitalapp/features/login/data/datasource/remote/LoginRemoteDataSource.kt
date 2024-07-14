package com.example.hospitalapp.features.login.data.datasource.remote

import com.example.hospitalapp.features.login.data.datasource.local.LoginLocalDataSource
import com.example.hospitalapp.features.login.data.model.ModelUser
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.framework.network.RetrofitService
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(private val retrofitService: RetrofitService,private val localDataSource: LoginLocalDataSource) {

    suspend fun login(email:String, password:String, deviceToken:String) : ResponseState<ModelUser>{
        val response = retrofitService.loginUser(email,password,deviceToken)
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }
}
