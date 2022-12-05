package com.shubham.yassirapp.presenter.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.yassirapp.domain.usecases.GetMovieDetailsUseCase
import com.shubham.yassirapp.domain.usecases.GetMoviesUseCase

class MovieListViewModelFactory(private val getMoviesUseCase : GetMoviesUseCase):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        return MovieListViewModel(getMoviesUseCase = getMoviesUseCase) as T
    }

}