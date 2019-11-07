package com.example.myapplication.model

data class Movie(val img: String, val title: String, val year: String, val genre: String, val description: String)

data class Movie2(val id: Int, val poster_path: String, val title: String, val original_title: String, val release_date: String, val vote_average: Double)

data class Result(val results: List<Movie2>)