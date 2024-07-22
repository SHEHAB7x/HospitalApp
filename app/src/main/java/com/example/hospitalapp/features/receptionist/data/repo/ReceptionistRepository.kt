package com.example.hospitalapp.features.receptionist.data.repo

import com.example.hospitalapp.features.receptionist.data.mapper.toDomain
import com.example.hospitalapp.features.receptionist.data.remote.IReceptionistRemoteDataSource
import com.example.hospitalapp.features.receptionist.domain.model.AllCalls
import com.example.hospitalapp.features.receptionist.domain.model.AllDoctors
import com.example.hospitalapp.features.receptionist.domain.model.CreateCall
import com.example.hospitalapp.features.receptionist.domain.model.LogoutCall
import com.example.hospitalapp.features.receptionist.domain.model.ShowCall
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class ReceptionistRepository @Inject constructor(private val remoteDataSource: IReceptionistRemoteDataSource) : IReceptionistRepository {
    override suspend fun getAllCalls(date: String): ResponseState<List<AllCalls>> {
        return when(val response = remoteDataSource.getAllCalls(date)){
            is ResponseState.Success ->
                ResponseState.Success(response.data.toDomain())
            is ResponseState.Error ->
                ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun createCall(
        patientName: String,
        doctorId: Int,
        age: String,
        phone: String,
        description: String
    ): ResponseState<CreateCall> {
        return when(val response = remoteDataSource.createCall(patientName, doctorId, age, phone, description)){
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun getAllDoctors(type: String): ResponseState<List<AllDoctors>> {
        return when(val response = remoteDataSource.getAllDoctors(type)){
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun showCall(id: Int): ResponseState<ShowCall> {
        return when(val response = remoteDataSource.showCall(id)){
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun logoutCall(id: Int): ResponseState<LogoutCall> {
        return when(val response = remoteDataSource.logoutCall(id)){
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

}