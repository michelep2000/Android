package com.example.myapplication.data.repository.local

class FavoriteMoviesLocalRepository(private val db: FavoritesDB) : LocalRepository {

    override suspend fun findMovie(id: Int): List<Favorites> {
        return db.favsDao().findMovie(id)
    }

    override suspend fun deleteOne(movie: Favorites){
        return db.favsDao().deleteOne(movie)
    }

    override suspend fun deleteAll(): Int {
        return db.favsDao().deleteAll()
    }

    override suspend fun orderByTitle(): List<Favorites> {
        return db.favsDao().orderByTitle()
    }

    override suspend fun orderByDate(): List<Favorites> {
        return db.favsDao().orderByDate()
    }

    override suspend fun getAll(): List<Favorites> {
        return db.favsDao().getAll()
    }

    override suspend fun addFavorite(movie: Favorites) {
        return db.favsDao().insert(movie)
    }


}

interface LocalRepository {
    suspend fun addFavorite(movie: Favorites)
    suspend fun getAll(): List<Favorites>
    suspend fun orderByDate(): List<Favorites>
    suspend fun orderByTitle(): List<Favorites>
    suspend fun deleteAll(): Int
    suspend fun deleteOne(movie: Favorites)
    suspend fun findMovie(id: Int): List<Favorites>
}