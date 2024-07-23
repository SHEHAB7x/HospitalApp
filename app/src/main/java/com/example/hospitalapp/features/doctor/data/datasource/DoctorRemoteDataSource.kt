package com.example.hospitalapp.features.doctor.data.datasource

import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
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
}