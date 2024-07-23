package com.example.hospitalapp.features.doctor.data.repo

import com.example.hospitalapp.features.doctor.data.datasource.IDoctorRemoteDataSource
import com.example.hospitalapp.features.doctor.data.mapper.toDomain
import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class DoctorRepository @Inject constructor(private val doctorRemoteDataSource: IDoctorRemoteDataSource) : IDoctorRepository {
    override suspend fun getAllCallsOfDoctor(): ResponseState<List<AllCallsOfDoctor>> {
        return when(val response = doctorRemoteDataSource.getAllCallsOfDoctor()){
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun acceptRejectCall(id: Int, status: String) {
        doctorRemoteDataSource.acceptRejectCall(id,status)
    }
}