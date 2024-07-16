package com.example.hospitalapp.features.specialist.di

import com.example.hospitalapp.features.specialist.data.remote.IReceptionistRemoteDataSource
import com.example.hospitalapp.features.specialist.data.remote.ReceptionistRemoteDataSource
import com.example.hospitalapp.features.specialist.data.repo.ReceptionistRepository
import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
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