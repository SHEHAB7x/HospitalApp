package com.example.hospitalapp.features.specialist.data.remote

import com.example.hospitalapp.features.specialist.data.model.ModelAllCalls
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
}