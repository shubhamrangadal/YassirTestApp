package com.shubham.yassirapp.presenter.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.shubham.yassirapp.domain.usecases.GetMovieDetailsUseCase

class MovieDetailsViewModelFactory(private val getMovieDetailsUseCase : GetMovieDetailsUseCase):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        return MovieDetailsViewModel(getMovieDetailsUseCase)as T
    }
}