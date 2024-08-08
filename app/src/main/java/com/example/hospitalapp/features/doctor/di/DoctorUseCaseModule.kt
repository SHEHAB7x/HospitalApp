package com.example.hospitalapp.features.doctor.di

import com.example.hospitalapp.features.doctor.domain.repo.IDoctorRepository
import com.example.hospitalapp.features.doctor.domain.usecase.AcceptRejectUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.AddNurseUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.AllCallsUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.AllCasesUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.CaseDetailsUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.MakeRequestUseCase
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

    @Provides
    @Singleton
    fun provideAllCasesUseCase(repository: IDoctorRepository) : AllCasesUseCase{
        return AllCasesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCaseDetailsUseCase(repository: IDoctorRepository) : CaseDetailsUseCase{
        return CaseDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddNurseUseCase(repository: IDoctorRepository) : AddNurseUseCase{
        return AddNurseUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMakeRequestUseCase(repository: IDoctorRepository) : MakeRequestUseCase{
        return MakeRequestUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutCallUseCase(repository: IDoctorRepository) : com.example.hospitalapp.features.doctor.domain.usecase.LogoutCallUseCase {
        return com.example.hospitalapp.features.doctor.domain.usecase.LogoutCallUseCase(repository)
    }
}