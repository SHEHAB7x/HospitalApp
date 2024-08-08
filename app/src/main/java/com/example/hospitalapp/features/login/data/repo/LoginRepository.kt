package com.example.hospitalapp.features.login.data.repo

import android.util.Log
import com.example.hospitalapp.features.login.data.datasource.local.ILoginLocalDataSource
import com.example.hospitalapp.features.login.data.datasource.local.LoginLocalDataSource
import com.example.hospitalapp.features.login.data.datasource.mappers.toDomain
import com.example.hospitalapp.features.login.data.datasource.remote.ILoginRemoteDataSource
import com.example.hospitalapp.features.login.data.datasource.remote.LoginRemoteDataSource
import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.features.login.domain.repository.ILoginRepository
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: ILoginRemoteDataSource,
    private val localDataSource: ILoginLocalDataSource
) : ILoginRepository {

    override suspend fun login(
        email: String,
        password: String,
        deviceToken: String
    ): ResponseState<User> {
        return when (val response = remoteDataSource.login(email, password, deviceToken)) {
            is ResponseState.Success -> {
                localDataSource.cacheLoginData(response.data.toDomain())
                ResponseState.Success(response.data.toDomain())
            }

            is ResponseState.Error -> {
                Log.e("TAG", "repositoryLogin: ${response.message}", )
                ResponseState.Error(response.message)
            }

            else -> ResponseState.Loading
        }
    }
}