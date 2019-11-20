package com.example.myapplication.data.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<Favorites>

    @Query("SELECT * FROM favorites ORDER by favorites.created")
    suspend fun orderByDate(): List<Favorites>

    @Query("SELECT * FROM favorites ORDER by favorites.title")
    suspend fun orderByTitle(): List<Favorites>

    @Query("DELETE FROM favorites")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM favorites WHERE favorites.id = :id")
    suspend fun findMovie(id: Int): List<Favorites>

    @Insert
    suspend fun insert(vararg fav: Favorites)

    @Delete
    suspend fun deleteOne(fav: Favorites)
}