package com.example.myapplication.ui.favorites

import com.example.myapplication.data.repository.local.Favorites
import com.example.myapplication.data.repository.local.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesPresenter(
    val view: FavoritesView,
    val localRepository: LocalRepository
){
    fun onLoad() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = localRepository.getAll()
            withContext(Dispatchers.Main) {
                view.setAdapter(favorites)
            }
        }
    }

}

interface FavoritesView {
    fun setAdapter(favorites: List<Favorites>)
}
