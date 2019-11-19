package com.example.myapplication.data.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<Favorites>

    @Insert
    suspend fun insert(vararg fav: Favorites)

    @Delete
    suspend fun delete(fav: Favorites)
}