package com.shubham.yassirapp.data.repository

import com.shubham.yassirapp.data.repository.datasource.MovieRemoteDataSource
import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.domain.models.MovieList
import com.shubham.yassirapp.domain.repositories.MoviesRepository


class MovieRepositoryImpl(private val movieRemoteDataSource : MovieRemoteDataSource) :
    MoviesRepository {

    override suspend fun getMoviesList(apiKey:String) : List<MovieList>? {
        return getMovieListFromApi(apiKey)
    }

    override suspend fun getMovieDetails(apiKey:String,movieId : Int) : MovieDetails? {
        return getMovieDetailsFromApi(apiKey,movieId)
    }

    suspend fun getMovieListFromApi(apiKey:String):List<MovieList>{
        lateinit var movieList: List<MovieList>

        try {
            val response = movieRemoteDataSource.getMovies(apiKey);
            val body = response.body()

            if (body!=null){
                movieList = body.results
            }

        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }

        return movieList
    }

    suspend fun getMovieDetailsFromApi(apiKey:String,movieId:Int): MovieDetails {
        lateinit var movieDetails: MovieDetails

        try {
            val response = movieRemoteDataSource.getMovieDetails(apiKey,movieId)
            val body = response.body()

            if (body!=null){
                movieDetails = body
            }

        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }

        return movieDetails

    }
}