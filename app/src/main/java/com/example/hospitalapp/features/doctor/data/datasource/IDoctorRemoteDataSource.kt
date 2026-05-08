package com.example.hospitalapp.features.doctor.data.datasource

import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.features.doctor.data.model.ModelAllCases
import com.example.hospitalapp.features.doctor.data.model.ModelCaseDetails
import com.example.hospitalapp.features.doctor.data.model.ModelLogoutCall
import com.example.hospitalapp.framework.network.ResponseState

interface IDoctorRemoteDataSource {
    suspend fun getAllCallsOfDoctor(): ResponseState<ModelAllCallsOfDoctor>
    suspend fun acceptRejectCall(id: Int, status: String)
    suspend fun getAllCases(): ResponseState<ModelAllCases>
    suspend fun getCaseDetails(caseId: Int): ResponseState<ModelCaseDetails>
    suspend fun addNurse(caseId: Int, nurseId: Int): ResponseState<Int>
    suspend fun makeRequest(
        caseId: Int,
        userId: Int,
        note: String,
        request: List<String>
    ): ResponseState<Int>
    suspend fun logoutCall(id : Int) : ResponseState<ModelLogoutCall>

}