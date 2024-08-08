package com.example.hospitalapp.features.doctor.data.repo

import com.example.hospitalapp.features.doctor.data.datasource.IDoctorRemoteDataSource
import com.example.hospitalapp.features.doctor.data.mapper.toDomain
import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.domain.model.AllCases
import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.domain.model.LogoutCall
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.features.receptionist.data.mapper.toDomain
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class DoctorRepository @Inject constructor(private val doctorRemoteDataSource: IDoctorRemoteDataSource) :
    IDoctorRepository {
    override suspend fun getAllCallsOfDoctor(): ResponseState<List<AllCallsOfDoctor>> {
        return when (val response = doctorRemoteDataSource.getAllCallsOfDoctor()) {
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun acceptRejectCall(id: Int, status: String) {
        doctorRemoteDataSource.acceptRejectCall(id, status)
    }

    override suspend fun getAllCases(): ResponseState<List<AllCases>> {
        return when (val response = doctorRemoteDataSource.getAllCases()) {
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun getCaseDetails(caseId: Int): ResponseState<CaseDetails> {
        return when (val response = doctorRemoteDataSource.getCaseDetails(caseId)) {
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun addNurse(caseId: Int, nurseId: Int): ResponseState<Int> {
        return when (val response = doctorRemoteDataSource.addNurse(caseId,nurseId)) {
            is ResponseState.Success -> ResponseState.Success(response.data)
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun makeRequest(
        caseId: Int,
        userId: Int,
        note: String,
        request: List<String>
    ): ResponseState<Int> {
        return when (val response = doctorRemoteDataSource.makeRequest(caseId,userId,note,request)) {
            is ResponseState.Success -> ResponseState.Success(response.data)
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

    override suspend fun logoutCall(id: Int): ResponseState<LogoutCall> {
        return when(val response = doctorRemoteDataSource.logoutCall(id)){
            is ResponseState.Success -> ResponseState.Success(response.data.toDomain())
            is ResponseState.Error -> ResponseState.Error(response.message)
            else -> ResponseState.Loading
        }
    }

}