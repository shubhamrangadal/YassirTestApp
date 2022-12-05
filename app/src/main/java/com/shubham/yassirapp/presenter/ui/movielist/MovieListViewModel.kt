package com.shubham.yassirapp.presenter.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.shubham.yassirapp.BuildConfig
import com.shubham.yassirapp.domain.models.MovieList
import com.shubham.yassirapp.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieListViewModel @Inject constructor(private val getMoviesUseCase : GetMoviesUseCase): ViewModel(){

    fun getMoviesList() = liveData<List<MovieList>?> {
        var movieList:List<MovieList>? =  getMoviesUseCase.execute(BuildConfig.API_KEY)
        emit(movieList)
    }

}