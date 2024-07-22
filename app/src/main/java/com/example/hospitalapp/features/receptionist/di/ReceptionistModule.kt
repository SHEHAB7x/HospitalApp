package com.example.hospitalapp.features.receptionist.di

import com.example.hospitalapp.features.receptionist.data.remote.IReceptionistRemoteDataSource
import com.example.hospitalapp.features.receptionist.data.remote.ReceptionistRemoteDataSource
import com.example.hospitalapp.features.receptionist.data.repo.ReceptionistRepository
import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReceptionistModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repository: ReceptionistRepository): IReceptionistRepository

    @Binds
    @Singleton
    abstract fun bindReceptionistRemoteDataSource(
        receptionistRemoteDataSource: ReceptionistRemoteDataSource
    ): IReceptionistRemoteDataSource
}