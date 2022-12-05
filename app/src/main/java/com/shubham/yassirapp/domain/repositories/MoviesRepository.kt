package com.shubham.yassirapp.domain.repositories

import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.models.MovieList

interface MoviesRepository {

    suspend fun getMoviesList(apiKey:String):List<MovieList>?

    suspend fun getMovieDetails(apiKey:String,movieId:Int): MovieDetails?

}