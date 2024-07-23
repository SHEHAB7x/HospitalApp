package com.example.hospitalapp.features.doctor.di

import com.example.hospitalapp.features.doctor.data.datasource.DoctorRemoteDataSource
import com.example.hospitalapp.features.doctor.data.datasource.IDoctorRemoteDataSource
import com.example.hospitalapp.features.doctor.data.repo.DoctorRepository
import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DoctorModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repository: DoctorRepository) : IDoctorRepository

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSource: DoctorRemoteDataSource) : IDoctorRemoteDataSource
}