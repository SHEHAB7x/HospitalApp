package com.example.hospitalapp.framework.network

import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.features.hr.data.models.ModelAllUsers
import com.example.hospitalapp.features.hr.data.models.ModelProfileUser
import com.example.hospitalapp.features.hr.data.models.ModelRegisterNewUser
import com.example.hospitalapp.features.login.data.model.ModelUser
import com.example.hospitalapp.features.receptionist.data.model.ModelAllCalls
import com.example.hospitalapp.features.receptionist.data.model.ModelAllDoctors
import com.example.hospitalapp.features.receptionist.data.model.ModelCreateCall
import com.example.hospitalapp.features.receptionist.data.model.ModelLogoutCall
import com.example.hospitalapp.features.receptionist.data.model.ModelShowCall
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @FormUrlEncoded
    @POST("calls")
    suspend fun createCall(
        @Field("patient_name") patientName : String,
        @Field("doctor_id") doctorId : Int,
        @Field("age") age : String,
        @Field("phone") phone : String,
        @Field("description") description : String
    ) : ModelCreateCall

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email : String,
        @Field("password") password: String,
        @Field("device_token") deviceToken: String
    ) : ModelUser

    @FormUrlEncoded
    @POST("show-profile")
    suspend fun showProfile(
        @Field("user_id") userId : Int
    ) : ModelProfileUser

    @GET("doctors")
    suspend fun getUserByType(
        @Query("type") type : String
    ): ModelAllUsers

    @GET("doctors")
    suspend fun getDoctors(
        @Query("type") type : String
    ): ModelAllDoctors

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("gender") gender: String,
        @Field("specialist") specialist: String,
        @Field("birthday") birthday: String,
        @Field("status") status: String,
        @Field("address") address: String,
        @Field("mobile") mobile: String,
        @Field("type") type: String
    ): ModelRegisterNewUser

    @GET("calls")
    suspend fun getCalls(
        @Query("date") date : String
    ) : ModelAllCalls

    @GET("calls/{id}")
    suspend fun showCall(
        @Path("id") callId : Int
    ): ModelShowCall

    @PUT("calls/{id}")
    suspend fun logoutCall(
        @Path("id") callId : Int
    ): ModelLogoutCall

    @GET("calls")
    suspend fun getAllCallsOfDoctor() : ModelAllCallsOfDoctor

    @FormUrlEncoded
    @PUT("calls-accept/{id}")
    suspend fun acceptRejectCall(
        @Path("id") callId : Int,
        @Field("status") status : String
        )
}