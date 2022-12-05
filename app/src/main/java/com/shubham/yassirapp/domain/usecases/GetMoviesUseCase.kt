package com.shubham.yassirapp.domain.usecases

import com.shubham.yassirapp.domain.models.MovieList
import com.shubham.yassirapp.domain.repositories.MoviesRepository

class GetMoviesUseCase(private val moviesRepository : MoviesRepository) {

    suspend fun execute(apiKey:String):List<MovieList>? = moviesRepository.getMoviesList(apiKey)

}