package com.example.hospitalapp.features.doctor.data.datasource

import com.example.hospitalapp.features.doctor.data.model.ModelAddNurse
import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.features.doctor.data.model.ModelAllCases
import com.example.hospitalapp.features.doctor.data.model.ModelCaseDetails
import com.example.hospitalapp.features.doctor.data.model.ModelLogoutCall
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DoctorApi {
    @PUT("calls/{id}")
    suspend fun logoutDoctorCall(
        @Path("id") callId: Int
    ): ModelLogoutCall

    @GET("calls")
    suspend fun getAllCallsOfDoctor(): ModelAllCallsOfDoctor

    @FormUrlEncoded
    @PUT("calls-accept/{id}")
    suspend fun acceptRejectCall(
        @Path("id") callId: Int,
        @Field("status") status: String
    )

    @GET("case")
    suspend fun getAllCases(): ModelAllCases

    @GET("case/{id}")
    suspend fun getCaseDetails(
        @Path("id") caseId : Int
    ): ModelCaseDetails

    @FormUrlEncoded
    @POST("add-nurse")
    suspend fun addNurse(
        @Field("call_id") callId : Int,
        @Field("user_id") nurseId : Int
    ) : ModelAddNurse

    @FormUrlEncoded
    @POST("make-request")
    suspend fun makeRequest(
        @Field("call_id") callId : Int,
        @Field("user_id") userId : Int,
        @Field("note") note: String,
        @Field("types[]") request: List<String>
    ) : ModelAddNurse
}