package com.shubham.yassirapp.data.di

import com.shubham.yassirapp.data.repository.MovieRepositoryImpl
import com.shubham.yassirapp.data.repository.datasource.MovieRemoteDataSource
import com.shubham.yassirapp.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieRemoteDataSource : MovieRemoteDataSource): MoviesRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }


}