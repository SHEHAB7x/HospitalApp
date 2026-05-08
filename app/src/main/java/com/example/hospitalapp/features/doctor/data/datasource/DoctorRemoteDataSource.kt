package com.example.hospitalapp.features.doctor.data.datasource

import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.features.doctor.data.model.ModelAllCases
import com.example.hospitalapp.features.doctor.data.model.ModelCaseDetails
import com.example.hospitalapp.features.doctor.data.model.ModelLogoutCall
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class DoctorRemoteDataSource @Inject constructor(private val doctorApi: DoctorApi) : IDoctorRemoteDataSource {
    override suspend fun getAllCallsOfDoctor(): ResponseState<ModelAllCallsOfDoctor>{
        val response = doctorApi.getAllCallsOfDoctor()
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun acceptRejectCall(id: Int, status: String) {
        doctorApi.acceptRejectCall(id,status)
    }

    override suspend fun getAllCases(): ResponseState<ModelAllCases> {
        val response = doctorApi.getAllCases()
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun getCaseDetails(caseId : Int): ResponseState<ModelCaseDetails> {
        val response = doctorApi.getCaseDetails(caseId)
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun addNurse(caseId: Int, nurseId: Int): ResponseState<Int> {
        val response = doctorApi.addNurse(caseId,nurseId)
        return if(response.status == 1){
            ResponseState.Success(response.status)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun makeRequest(
        caseId: Int,
        userId: Int,
        note: String,
        request: List<String>
    ): ResponseState<Int> {
        val response = doctorApi.makeRequest(caseId,userId,note,request)
        return if(response.status == 1){
            ResponseState.Success(response.status)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun logoutCall(id: Int): ResponseState<ModelLogoutCall> {
        val response = doctorApi.logoutDoctorCall(id)
        return if (response.status == 1)
            ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }

}