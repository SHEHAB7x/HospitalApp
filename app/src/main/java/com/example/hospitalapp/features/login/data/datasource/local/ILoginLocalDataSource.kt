package com.example.hospitalapp.features.login.data.datasource.local

import com.example.hospitalapp.features.login.domain.models.User

interface ILoginLocalDataSource {
    suspend fun cacheLoginData(user: User)
}