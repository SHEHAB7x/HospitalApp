package com.example.hospitalapp.features.receptionist.domain.repo

import com.example.hospitalapp.features.receptionist.domain.model.AllCalls
import com.example.hospitalapp.features.receptionist.domain.model.AllDoctors
import com.example.hospitalapp.features.receptionist.domain.model.CreateCall
import com.example.hospitalapp.features.receptionist.domain.model.LogoutCall
import com.example.hospitalapp.features.receptionist.domain.model.ShowCall
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