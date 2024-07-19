package com.example.hospitalapp.features.specialist.domain.repo

import com.example.hospitalapp.features.specialist.data.model.ModelAllDoctors
import com.example.hospitalapp.features.specialist.data.model.ModelCreateCall
import com.example.hospitalapp.features.specialist.domain.model.AllCalls
import com.example.hospitalapp.features.specialist.domain.model.AllDoctors
import com.example.hospitalapp.features.specialist.domain.model.CreateCall
import com.example.hospitalapp.features.specialist.domain.model.LogoutCall
import com.example.hospitalapp.features.specialist.domain.model.ShowCall
import com.example.hospitalapp.framework.network.ResponseState

interface IReceptionistRepository {
    suspend fun getAllCalls(date: String): ResponseState<List<AllCalls>>
    suspend fun createCall(
        patientName: String,
        doctorId: Int,
        age: String,
        phone: String,
        description: String
    ): ResponseState<CreateCall>

    suspend fun getAllDoctors(type : String) : ResponseState<List<AllDoctors>>
    suspend fun showCall(id : Int) : ResponseState<ShowCall>
    suspend fun logoutCall(id : Int) : ResponseState<LogoutCall>
}