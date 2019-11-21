package com.example.myapplication.ui.search.detail

import android.util.Log
import com.example.myapplication.data.repository.local.LocalRepository
import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.model.MovieDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FinderDetailPresenter(
    private val view: FinderDetailView,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {

    fun onLoadDetail(id: Int?) {
        if (id == null) return
        CoroutineScope(Dispatchers.IO).launch {
            val responseGeneral = remoteRepository.getMovieDetail(id)
            val responseCastCrew = remoteRepository.getMovieCastAndCrew(id)
            withContext(Dispatchers.Main) {
                view.setViewValues(responseGeneral, responseCastCrew)
                setStar(id)

            }
        }
    }

    fun enableFavorite(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val favorite = remoteRepository.getFavorite(movieId)
            localRepository.addFavorite(favorite)
            withContext(Dispatchers.Main) {
                setStar(movieId)
            }
        }
    }

    fun disabledFavorite(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val favorite = remoteRepository.getFavorite(movieId)
            localRepository.deleteOne(favorite)
            withContext(Dispatchers.Main) {
                setStar(movieId)
            }
        }
    }

    private fun setStar(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val isFavorite = localRepository.findMovie(movieId).isNotEmpty()
            withContext(Dispatchers.Main) {
                view.setFavorite(isFavorite)
            }
        }
    }
}

interface FinderDetailView {

    fun setViewValues(
        general: MovieDetail,
        cast_crew: MovieDetail
    )

    fun setFavorite(isTrue: Boolean)

}
