package com.example.myapplication.data.repository.local

import com.example.myapplication.model.Movie

class FavoriteMoviesLocalRepository(private val db: FavoritesDB) : LocalRepository {

    override suspend fun findMovie(id: Int): List<Movie> {
        return db.favsDao().findMovie(id)
    }

    override suspend fun deleteOne(movie: Movie){
        return db.favsDao().deleteOne(movie)
    }

    override suspend fun deleteAll(): Int {
        return db.favsDao().deleteAll()
    }

    override suspend fun orderByTitle(): List<Movie> {
        return db.favsDao().orderByTitle()
    }

    override suspend fun orderByDate(): List<Movie> {
        return db.favsDao().orderByDate()
    }

    override suspend fun getAll(): List<Movie> {
        return db.favsDao().getAll()
    }

    override suspend fun addFavorite(movie: Movie) {
        return db.favsDao().insert(movie)
    }


}

interface LocalRepository {
    suspend fun addFavorite(movie: Movie)
    suspend fun getAll(): List<Movie>
    suspend fun orderByDate(): List<Movie>
    suspend fun orderByTitle(): List<Movie>
    suspend fun deleteAll(): Int
    suspend fun deleteOne(movie: Movie)
    suspend fun findMovie(id: Int): List<Movie>
}