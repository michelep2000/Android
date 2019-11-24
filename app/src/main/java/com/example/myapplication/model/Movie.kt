package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val poster_path: String,
    val title: String,
    val original_title: String,
    val release_date: String,
    val vote_average: Double,
    val created: Long? = System.currentTimeMillis()
)

data class Result(val results: List<Movie>)

