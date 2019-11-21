package com.example.myapplication.ui.favorites

import com.example.myapplication.data.repository.local.LocalRepository
import com.example.myapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesPresenter(
    private val view: FavoritesView,
    private val localRepository: LocalRepository
){
    fun onLoad() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = localRepository.getAll()
            withContext(Dispatchers.Main) {
                view.setAdapter(favorites)
            }
        }
    }

    fun onMovieClicked(id: Int) {
        if (id == null) return
        view.openDetail(id)
    }

    fun orderByTitleClicked() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = localRepository.orderByTitle()
            withContext(Dispatchers.Main) {
                view.setAdapter(favorites)
            }
        }
    }

    fun orderByDateClicked() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = localRepository.orderByDate()
            withContext(Dispatchers.Main) {
                view.setAdapter(favorites)
            }
        }
    }

    fun deleteClicked() {
        CoroutineScope(Dispatchers.IO).launch {
            localRepository.deleteAll()
            withContext(Dispatchers.Main) {
                onLoad()
            }
        }
    }
}

interface FavoritesView {
    fun setAdapter(favorites: List<Movie>)
    fun openDetail(id: Int)
}
