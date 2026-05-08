package com.example.hospitalapp.features.doctor.domain.repo

import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.domain.model.AllCases
import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.domain.model.LogoutCall
import com.example.hospitalapp.framework.network.ResponseState

interface IDoctorRepository {
    suspend fun getAllCallsOfDoctor(): ResponseState<List<AllCallsOfDoctor>>
    suspend fun acceptRejectCall(id: Int, status: String)
    suspend fun getAllCases(): ResponseState<List<AllCases>>
    suspend fun getCaseDetails(caseId: Int): ResponseState<CaseDetails>
    suspend fun addNurse(caseId: Int, nurseId: Int): ResponseState<Int>
    suspend fun makeRequest(
        caseId: Int,
        userId: Int,
        note: String,
        request: List<String>
    ): ResponseState<Int>

    suspend fun logoutCall(id : Int) : ResponseState<LogoutCall>
}