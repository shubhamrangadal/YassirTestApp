package com.shubham.yassirapp.data.repository.datasource


import com.shubham.yassirapp.domain.models.Movie
import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.models.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies(apiKey:String):Response<Movie>

    suspend fun getMovieDetails(apiKey:String,movieId:Int):Response<MovieDetails>
}