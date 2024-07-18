package com.example.hospitalapp.features.hr.di

import com.example.hospitalapp.features.hr.data.datasource.HrRemoteDataSource
import com.example.hospitalapp.features.hr.data.datasource.IHrRemoteDataSource
import com.example.hospitalapp.features.hr.data.repo.HrRepository
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HrModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repository: HrRepository): IHrRepository

    @Binds
    @Singleton
    abstract fun bindHrRemoteDataSource(hrRemoteDataSource: HrRemoteDataSource) : IHrRemoteDataSource
}