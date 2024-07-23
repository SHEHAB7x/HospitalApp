package com.example.hospitalapp.features.doctor.data.datasource

import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.framework.network.ResponseState

interface IDoctorRemoteDataSource {
    suspend fun getAllCallsOfDoctor() : ResponseState<ModelAllCallsOfDoctor>
    suspend fun acceptRejectCall(id : Int,status : String)
}