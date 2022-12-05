package com.shubham.yassirapp.data.di

import com.shubham.yassirapp.data.api.RetrofitService
import com.shubham.yassirapp.data.repository.datasource.MovieRemoteDataSource
import com.shubham.yassirapp.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule() {

    @Singleton
    @Provides
    fun provideRemoteDataSource(retrofitService : RetrofitService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(retrofitService=retrofitService)
    }

}