package com.example.hospitalapp.features.doctor.di

import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.features.doctor.domain.usecase.AcceptRejectUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.AllCallsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DoctorUseCaseModule {

    @Provides
    @Singleton
    fun provideAllCallsUseCase(repository: IDoctorRepository) : AllCallsUseCase{
        return AllCallsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAcceptRejectUseCase(repository: IDoctorRepository) : AcceptRejectUseCase{
        return AcceptRejectUseCase(repository)
    }
}