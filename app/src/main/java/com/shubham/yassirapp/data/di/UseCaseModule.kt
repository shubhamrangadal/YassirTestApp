package com.shubham.yassirapp.data.di


import com.shubham.yassirapp.domain.repositories.MoviesRepository
import com.shubham.yassirapp.domain.usecases.GetMovieDetailsUseCase
import com.shubham.yassirapp.domain.usecases.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(moviesRepository : MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository)
    }


    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository : MoviesRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(moviesRepository)
    }

}