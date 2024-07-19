package com.example.hospitalapp.features.specialist.data.remote

import com.example.hospitalapp.features.specialist.data.model.ModelAllCalls
import com.example.hospitalapp.features.specialist.data.model.ModelAllDoctors
import com.example.hospitalapp.features.specialist.data.model.ModelCreateCall
import com.example.hospitalapp.features.specialist.data.model.ModelLogoutCall
import com.example.hospitalapp.features.specialist.data.model.ModelShowCall
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.framework.network.RetrofitService
import javax.inject.Inject

class ReceptionistRemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) : IReceptionistRemoteDataSource {
    override suspend fun getAllCalls(date: String): ResponseState<ModelAllCalls> {
        val response = retrofitService.getCalls(date)
       return if(response.status == 1)
           ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }

    override suspend fun createCall(
        patientName: String,
        doctorId: Int,
        age: String,
        phone: String,
        description: String
    ): ResponseState<ModelCreateCall> {
        val response = retrofitService.createCall(patientName, doctorId, age, phone, description)
        return if(response.status == 1)
            ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }

    override suspend fun getAllDoctors(type: String): ResponseState<ModelAllDoctors> {
        val response = retrofitService.getDoctors(type)
        return if(response.status == 1)
            ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }

    override suspend fun showCall(id: Int): ResponseState<ModelShowCall> {
        val response = retrofitService.showCall(id)
        return if (response.status == 1)
            ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }

    override suspend fun logoutCall(id: Int): ResponseState<ModelLogoutCall> {
        val response = retrofitService.logoutCall(id)
        return if (response.status == 1)
            ResponseState.Success(response)
        else
            ResponseState.Error(response.message)
    }
}