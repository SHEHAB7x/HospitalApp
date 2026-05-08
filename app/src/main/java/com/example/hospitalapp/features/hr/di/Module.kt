package com.example.hospitalapp.features.hr.di

import com.example.hospitalapp.features.hr.data.datasource.HrRemoteDataSource
import com.example.hospitalapp.features.hr.data.datasource.IHrRemoteDataSource
import com.example.hospitalapp.features.hr.data.repo.HrRepository
import com.example.hospitalapp.features.hr.domain.repo.IHrRepository
import com.example.hospitalapp.framework.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    /*@Provides
    @Singleton
    fun bindHrRemoteDataSource(retrofitService: RetrofitService): IHrRemoteDataSource {
        return HrRemoteDataSource(retrofitService)
    }

    @Provides
    @Singleton
    fun bindHrRepo(hrRemoteDataSource: IHrRemoteDataSource) : IHrRepository{
        return HrRepository(hrRemoteDataSource)
    }*/
}