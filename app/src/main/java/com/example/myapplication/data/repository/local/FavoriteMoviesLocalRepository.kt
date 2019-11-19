package com.example.myapplication.data.repository.local

class FavoriteMoviesLocalRepository(val db: FavoritesDB) : LocalRepository {

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
}