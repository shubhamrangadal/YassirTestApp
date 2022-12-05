package com.shubham.yassirapp.presenter.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shubham.yassirapp.BuildConfig
import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.usecases.GetMovieDetailsUseCase
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase : GetMovieDetailsUseCase): ViewModel() {

    fun getMovieDetails(movieId: Int) = liveData<MovieDetails?> {
        val movieDetails = getMovieDetailsUseCase.execute(BuildConfig.API_KEY,movieId = movieId)
        emit(movieDetails)
    }

}