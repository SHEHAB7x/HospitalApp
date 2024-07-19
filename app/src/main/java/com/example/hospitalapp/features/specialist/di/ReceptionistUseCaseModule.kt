package com.example.hospitalapp.features.specialist.di

import com.example.hospitalapp.features.specialist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.features.specialist.domain.usecase.CallsUseCase
import com.example.hospitalapp.features.specialist.domain.usecase.CreateCallUseCase
import com.example.hospitalapp.features.specialist.domain.usecase.LogoutCallUseCase
import com.example.hospitalapp.features.specialist.domain.usecase.SelectDoctorUseCase
import com.example.hospitalapp.features.specialist.domain.usecase.ShowCallUseCase
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

    @Provides
    @Singleton
    fun provideCreateCallUseCase(repository: IReceptionistRepository) : CreateCallUseCase{
        return CreateCallUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSelectDoctorUseCase(repository: IReceptionistRepository) : SelectDoctorUseCase{
        return SelectDoctorUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideShowCallUseCase(repository: IReceptionistRepository) : ShowCallUseCase{
        return ShowCallUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutCallUseCase(repository: IReceptionistRepository) : LogoutCallUseCase{
        return LogoutCallUseCase(repository)
    }
}