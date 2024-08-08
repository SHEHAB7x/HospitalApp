package com.example.hospitalapp.features.doctor.data.datasource

import com.example.hospitalapp.features.doctor.data.model.ModelAddNurse
import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.features.doctor.data.model.ModelAllCases
import com.example.hospitalapp.features.doctor.data.model.ModelCaseDetails
import com.example.hospitalapp.features.doctor.data.model.ModelLogoutCall
import com.example.hospitalapp.features.doctor.domain.model.LogoutCall
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.framework.network.RetrofitService
import javax.inject.Inject

class DoctorRemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) : IDoctorRemoteDataSource {
    override suspend fun getAllCallsOfDoctor(): ResponseState<ModelAllCallsOfDoctor>{
        val response = retrofitService.getAllCallsOfDoctor()
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun acceptRejectCall(id: Int, status: String) {
        retrofitService.acceptRejectCall(id,status)
    }

    override suspend fun getAllCases(): ResponseState<ModelAllCases> {
        val response = retrofitService.getAllCases()
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun getCaseDetails(caseId : Int): ResponseState<ModelCaseDetails> {
        val response = retrofitService.getCaseDetails(caseId)
        return if(response.status == 1){
            ResponseState.Success(response)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun addNurse(caseId: Int, nurseId: Int): ResponseState<Int> {
        val response = retrofitService.addNurse(caseId,nurseId)
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
        val response = retrofitService.makeRequest(caseId,userId,note,request)
        return if(response.status == 1){
            ResponseState.Success(response.status)
        }else{
            ResponseState.Error(response.message)
        }
    }

    override suspend fun logoutCall(id: Int): ResponseState<ModelLogoutCall> {
        val response = retrofitService.logoutDoctorCall(id)
        return if (response.status == 1)
            ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }

}