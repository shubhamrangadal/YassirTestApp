package com.shubham.yassirapp.data.repository.datasourceimpl

import com.shubham.yassirapp.data.api.RetrofitService
import com.shubham.yassirapp.data.repository.datasource.MovieRemoteDataSource
import com.shubham.yassirapp.domain.models.Movie
import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.models.MovieList

import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val retrofitService : RetrofitService):
    MovieRemoteDataSource {

    override suspend fun getMovies(apiKey:String) : Response<Movie> {
        return retrofitService.getMovieList(apiKey)
    }

    override suspend fun getMovieDetails(apiKey:String,movieId : Int) : Response<MovieDetails> {
        return retrofitService.getMovieDetails(movieId = movieId, apiKey = apiKey)
    }
}