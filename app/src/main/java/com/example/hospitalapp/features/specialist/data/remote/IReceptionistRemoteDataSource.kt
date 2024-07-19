package com.example.hospitalapp.features.specialist.data.remote

import com.example.hospitalapp.features.specialist.data.model.ModelAllCalls
import com.example.hospitalapp.features.specialist.data.model.ModelAllDoctors
import com.example.hospitalapp.features.specialist.data.model.ModelCreateCall
import com.example.hospitalapp.features.specialist.data.model.ModelLogoutCall
import com.example.hospitalapp.features.specialist.data.model.ModelShowCall
import com.example.hospitalapp.framework.network.ResponseState

interface IReceptionistRemoteDataSource {
    suspend fun getAllCalls(date: String): ResponseState<ModelAllCalls>
    suspend fun createCall(
        patientName: String,
        doctorId: Int,
        age: String,
        phone: String,
        description: String
    ) : ResponseState<ModelCreateCall>
    suspend fun getAllDoctors(type : String) : ResponseState<ModelAllDoctors>
    suspend fun showCall(id : Int) : ResponseState<ModelShowCall>
    suspend fun logoutCall(id : Int) : ResponseState<ModelLogoutCall>
}