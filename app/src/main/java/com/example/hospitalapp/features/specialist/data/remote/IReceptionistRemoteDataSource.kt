package com.example.hospitalapp.features.specialist.data.remote

import com.example.hospitalapp.features.specialist.data.model.ModelAllCalls
import com.example.hospitalapp.framework.network.ResponseState

interface IReceptionistRemoteDataSource {
    suspend fun getAllCalls(date : String) : ResponseState<ModelAllCalls>
}