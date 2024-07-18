package com.example.hospitalapp.features.login.data.datasource.local

import android.content.SharedPreferences
import com.example.hospitalapp.features.login.data.datasource.remote.ILoginRemoteDataSource
import com.example.hospitalapp.features.login.data.model.ModelUser
import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.framework.database.MySharedPreferences
import com.example.hospitalapp.framework.network.ResponseState
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(private val sharedPreferences: SharedPreferences ) : ILoginLocalDataSource{
   override suspend fun cacheLoginData(user: User){
        MySharedPreferences.setUserType(user.type ?: "")
        MySharedPreferences.setUserName(user.firstName+" "+user.lastName)
        MySharedPreferences.setUserId(user.id)
        MySharedPreferences.setUserPhone(user.mobile)
        MySharedPreferences.setUserEmail(user.email)
        MySharedPreferences.setUserBirthday(user.birthday)
        MySharedPreferences.setUserAddress(user.address)
        MySharedPreferences.setUserStatus(user.status)
        MySharedPreferences.setUserGender(user.gender)
        MySharedPreferences.setUserToken(user.accessToken)
    }

}