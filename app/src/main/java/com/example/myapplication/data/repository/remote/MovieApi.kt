package com.example.myapplication.data.repository.remote

import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.Result
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("search/movie?api_key=012347d64af54be3adf8138a1dbefd02&query=a")
   suspend fun searchMovies(@Query("query") term: String): Response<Result>

   @GET("movie/{id}?api_key=012347d64af54be3adf8138a1dbefd02")
    suspend fun getMovieDetail(@Path("id") movieId: Int?): Response<MovieDetail>

    @GET("movie/{id}/credits?api_key=012347d64af54be3adf8138a1dbefd02")
    suspend fun getMovieCastAndCrew(@Path("id") movieId: Int?): Response<MovieDetail>
}


object RetrofitFactory {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(MovieApi::class.java)
    }
}