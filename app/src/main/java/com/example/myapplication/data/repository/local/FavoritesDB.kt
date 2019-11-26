package com.example.myapplication.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class FavoritesDB : RoomDatabase() {
    abstract fun favDao(): FavoritesDao
}

object FavoritesFactory {
    fun get(context: Context): FavoritesDB {
        return Room
            .databaseBuilder(
                context,
                FavoritesDB::class.java, "database-movies"
            ).build()
    }

}