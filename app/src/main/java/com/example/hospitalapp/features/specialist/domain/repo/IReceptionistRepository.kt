package com.example.hospitalapp.features.specialist.domain.repo

import com.example.hospitalapp.features.specialist.domain.model.AllCalls
import com.example.hospitalapp.framework.network.ResponseState

interface IReceptionistRepository {
    suspend fun getAllCalls(date : String) : ResponseState<List<AllCalls>>
}