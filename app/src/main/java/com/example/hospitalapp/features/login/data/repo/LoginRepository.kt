package com.example.hospitalapp.features.login.data.repo
import com.example.hospitalapp.features.login.data.datasource.local.LoginLocalDataSource
import com.example.hospitalapp.features.login.data.datasource.mappers.toDomain
import com.example.hospitalapp.features.login.data.datasource.remote.LoginRemoteDataSource
import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.features.login.domain.repository.ILoginRepository
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class LoginRepository @Inject constructor (private val remoteDataSource: LoginRemoteDataSource,private val localDataSource: LoginLocalDataSource) : ILoginRepository {
    override suspend fun login(
        email: String,
        password: String,
        deviceToken: String
    ): ResponseState<User> {
        return when (val response = remoteDataSource.login(email,password,deviceToken)){
            is ResponseState.Success ->{
                localDataSource.cacheLoginData(response.data.toDomain())
                ResponseState.Success(response.data.toDomain())
            }
            is ResponseState.Error -> {
                ResponseState.Error(response.message)
            }
            else -> ResponseState.Loading
        }
    }
}