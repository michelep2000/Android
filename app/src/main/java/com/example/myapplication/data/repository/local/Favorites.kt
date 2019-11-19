package com.example.myapplication.data.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorites(
    @PrimaryKey val id: Int,
    val poster_path: String,
    val title: String,
    val original_title: String,
    val release_date: String,
    val vote_average: Double,
    val created: Long? = System.currentTimeMillis()
)