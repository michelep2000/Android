package com.example.myapplication.data.repository.remote

import android.accounts.NetworkErrorException
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.Result

class RetrofitRemoteRepository(val movieApi: MovieApi) : RemoteRepository {

    override suspend fun searchMovies(term: String): Result {
        val response = movieApi.searchMovies(term)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }

    override suspend fun getMovieDetail(movieId: Int?): MovieDetail {
        val response = movieApi.getMovieDetail(movieId)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }

    }

    override suspend fun getFavorite(movieId: Int?): Movie {
        val response = movieApi.getFavorite(movieId)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }

    override suspend fun getMovieCastAndCrew(movieId: Int?): MovieDetail {
        val response = movieApi.getMovieCastAndCrew(movieId)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }
}

interface RemoteRepository {
    suspend fun searchMovies(term: String): Result
    suspend fun getMovieDetail(movieId: Int?): MovieDetail
    suspend fun getMovieCastAndCrew(movieId: Int?): MovieDetail
    suspend fun getFavorite(movieId: Int?): Movie
}


