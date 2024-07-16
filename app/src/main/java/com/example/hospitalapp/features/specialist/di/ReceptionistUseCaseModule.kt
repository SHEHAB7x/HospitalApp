package com.example.hospitalapp.features.specialist.di

import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.features.specialist.domain.usecase.CallsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReceptionistUseCaseModule {
    @Provides
    @Singleton
    fun provideCallsUseCase(repository: IReceptionistRepository) : CallsUseCase{
        return CallsUseCase(repository)
    }

}