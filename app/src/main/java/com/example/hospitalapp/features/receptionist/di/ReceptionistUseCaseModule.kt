package com.example.hospitalapp.features.receptionist.di

import com.example.hospitalapp.features.receptionist.domain.repo.IReceptionistRepository
import com.example.hospitalapp.features.receptionist.domain.usecase.CallsUseCase
import com.example.hospitalapp.features.receptionist.domain.usecase.CreateCallUseCase
import com.example.hospitalapp.features.receptionist.domain.usecase.LogoutCallUseCase
import com.example.hospitalapp.features.receptionist.domain.usecase.SelectDoctorUseCase
import com.example.hospitalapp.features.receptionist.domain.usecase.ShowCallUseCase
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