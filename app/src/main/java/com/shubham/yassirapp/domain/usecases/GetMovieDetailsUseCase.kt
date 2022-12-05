package com.shubham.yassirapp.domain.usecases

import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.repositories.MoviesRepository

class GetMovieDetailsUseCase(private val moviesRepository : MoviesRepository) {

    suspend fun execute(apiKey:String,movieId:Int): MovieDetails? = moviesRepository.getMovieDetails(apiKey,movieId)

}