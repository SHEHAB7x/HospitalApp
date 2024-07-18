package com.example.hospitalapp.features.hr.data.repo

import com.example.hospitalapp.features.hr.data.datasource.HrRemoteDataSource
import com.example.hospitalapp.features.hr.data.datasource.IHrRemoteDataSource
import com.example.hospitalapp.features.hr.data.mapper.toDomain
import com.example.hospitalapp.features.hr.data.models.ModelRegisterNewUser
import com.example.hospitalapp.features.hr.domain.models.RegisterNewUser
import com.example.hospitalapp.features.hr.domain.models.User
import com.example.hospitalapp.features.hr.domain.models.UserProfile
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class HrRepository @Inject constructor(
    private val hrRemoteDataSource: IHrRemoteDataSource
) : IHrRepository {
    override suspend fun getUserProfile(id: Int): ResponseState<UserProfile> {
        return when (val response = hrRemoteDataSource.getUserProfile(id)) {
            is ResponseState.Success ->
                ResponseState.Success(response.data.toDomain())

            is ResponseState.Error ->
                ResponseState.Error(response.message)

            else -> ResponseState.Loading
        }
    }

    override suspend fun getAllEmployee(type: String): ResponseState<List<User>> {
        return when (val response = hrRemoteDataSource.getAllUsers(type)) {
            is ResponseState.Success ->
                ResponseState.Success(response.data.toDomain())

            is ResponseState.Error ->
                ResponseState.Error(response.message)

            else -> ResponseState.Loading
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
    ): ResponseState<RegisterNewUser> {
        return when (val response = hrRemoteDataSource.registerNewUser(
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
        )) {
            is ResponseState.Success ->
                ResponseState.Success(response.data.toDomain())

            is ResponseState.Error ->
                ResponseState.Error(response.message)

            else -> ResponseState.Loading
        }
    }


}