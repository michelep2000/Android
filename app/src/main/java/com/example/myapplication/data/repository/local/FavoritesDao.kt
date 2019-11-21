package com.example.myapplication.data.repository.local

import androidx.room.*
import com.example.myapplication.model.Movie

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie ORDER by movie.created")
    suspend fun orderByDate(): List<Movie>

    @Query("SELECT * FROM movie ORDER by movie.title")
    suspend fun orderByTitle(): List<Movie>

    @Query("DELETE FROM movie")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    suspend fun findMovie(id: Int): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg fav: Movie)

    @Delete
    suspend fun deleteOne(fav: Movie)
}