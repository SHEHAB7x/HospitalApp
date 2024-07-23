package com.example.hospitalapp.features.doctor.domain.repo

import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.framework.network.ResponseState

interface IDoctorRepository {
    suspend fun getAllCallsOfDoctor() : ResponseState<List<AllCallsOfDoctor>>
    suspend fun acceptRejectCall(id : Int, status : String)
}