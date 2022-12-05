package com.shubham.yassirapp.data.api


import com.shubham.yassirapp.domain.models.Movie
import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.models.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/discover/movie")
    suspend fun getMovieList(@Query(value = "api_key")apiKey:String): Response<Movie>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, @Query(value = "api_key")apiKey:String): Response<MovieDetails>

}