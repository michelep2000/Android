package com.example.myapplication.model

import java.util.*

data class MovieDetail(
    val backdrop_path: String,
    val title: String,
    val original_title: String,
    val release_date: String,
    val vote_average: Double,
    val overview: String,
    val genres: List<Genre>,
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Genre(val name: String)
data class Cast(val name: String)
data class Crew(val name: String, val job: String)


