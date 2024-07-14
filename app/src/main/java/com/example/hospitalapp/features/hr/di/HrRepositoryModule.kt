package com.example.hospitalapp.features.hr.di

import com.example.hospitalapp.features.hr.data.repo.HrRepository
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HrRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repository: HrRepository): IHrRepository
}