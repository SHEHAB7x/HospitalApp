package com.example.hospitalapp.features.login.di

import com.example.hospitalapp.features.login.data.repo.LoginRepository
import com.example.hospitalapp.features.login.domain.repository.ILoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repository: LoginRepository): ILoginRepository
}
