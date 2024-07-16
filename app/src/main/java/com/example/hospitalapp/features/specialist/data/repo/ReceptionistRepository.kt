package com.example.hospitalapp.features.specialist.data.repo

import com.example.hospitalapp.features.specialist.data.mapper.toDomain
import com.example.hospitalapp.features.specialist.data.remote.IReceptionistRemoteDataSource
import com.example.hospitalapp.features.specialist.data.remote.ReceptionistRemoteDataSource
import com.example.hospitalapp.features.specialist.domain.model.AllCalls
import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
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
}