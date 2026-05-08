package com.example.hospitalapp.features.hr.data.datasource

import com.example.hospitalapp.features.hr.data.models.ModelAllUsers
import com.example.hospitalapp.features.hr.data.models.ModelProfileUser
import com.example.hospitalapp.features.hr.data.models.ModelRegisterNewUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HrApi {
    @GET("doctors")
    suspend fun getUserByType(
        @Query("type") type: String
    ): ModelAllUsers

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

    @FormUrlEncoded
    @POST("show-profile")
    suspend fun showProfile(
        @Field("user_id") userId: Int
    ): ModelProfileUser
}