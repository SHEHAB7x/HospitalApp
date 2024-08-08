package com.example.hospitalapp.features.login.di

import com.example.hospitalapp.features.login.data.datasource.local.ILoginLocalDataSource
import com.example.hospitalapp.features.login.data.datasource.local.LoginLocalDataSource
import com.example.hospitalapp.features.login.data.datasource.remote.ILoginRemoteDataSource
import com.example.hospitalapp.features.login.data.datasource.remote.LoginRemoteDataSource
import com.example.hospitalapp.features.login.data.repo.LoginRepository
import com.example.hospitalapp.features.login.domain.repository.ILoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repository: LoginRepository): ILoginRepository


    @Binds
    @Singleton
    abstract fun bindLoginRemoteDataSource(
        loginRemoteDataSource: LoginRemoteDataSource
    ): ILoginRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLoginLocalDataSource(loginLocalDataSource: LoginLocalDataSource) : ILoginLocalDataSource
}
