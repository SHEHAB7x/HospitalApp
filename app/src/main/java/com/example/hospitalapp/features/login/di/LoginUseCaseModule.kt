package com.example.hospitalapp.features.login.di

import com.example.hospitalapp.features.login.domain.usecase.LoginUseCase
import com.example.hospitalapp.features.login.domain.repository.ILoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: ILoginRepository
    ): LoginUseCase {
        return LoginUseCase(repository)
    }
}
