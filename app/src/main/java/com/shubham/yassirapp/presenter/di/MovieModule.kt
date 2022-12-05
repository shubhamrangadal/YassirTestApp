package com.shubham.yassirapp.presenter.di

import com.shubham.yassirapp.domain.usecases.GetMovieDetailsUseCase
import com.shubham.yassirapp.domain.usecases.GetMoviesUseCase
import com.shubham.yassirapp.presenter.ui.moviedetails.MovieDetailsViewModelFactory
import com.shubham.yassirapp.presenter.ui.movielist.MovieListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieListViewModelFactory(getMoviesUseCase : GetMoviesUseCase):MovieListViewModelFactory{
        return MovieListViewModelFactory(getMoviesUseCase = getMoviesUseCase)
    }

    @MovieScope
    @Provides
    fun provideMovieDetailsViewModelFactory(getMovieDetailsUseCase : GetMovieDetailsUseCase):MovieDetailsViewModelFactory{
        return MovieDetailsViewModelFactory(getMovieDetailsUseCase = getMovieDetailsUseCase)
    }

}