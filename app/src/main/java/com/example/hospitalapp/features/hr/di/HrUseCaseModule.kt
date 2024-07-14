package com.example.hospitalapp.features.hr.di

import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import com.example.hospitalapp.features.hr.domain.usecase.AllUsersByTypeUseCase
import com.example.hospitalapp.features.hr.domain.usecase.HrProfileUseCase
import com.example.hospitalapp.features.hr.domain.usecase.RegisterNewUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HrUseCaseModule {

    @Provides
    @Singleton
    fun provideProfileUseCase(repository: IHrRepository) : HrProfileUseCase{
        return HrProfileUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAllUsersUseCase(repository: IHrRepository) : AllUsersByTypeUseCase{
        return AllUsersByTypeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterNewUserUseCase(repository: IHrRepository) : RegisterNewUserUseCase{
        return RegisterNewUserUseCase(repository)
    }
}