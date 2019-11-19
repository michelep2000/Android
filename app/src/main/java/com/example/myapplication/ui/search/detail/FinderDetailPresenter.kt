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

            }
        }
    }

    fun onFavoritesClicked(movieId: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            val favorite = remoteRepository.getFavorite(movieId)
            Log.e("ID" ,"${favorite.id}")
            localRepository.addFavorite(favorite)

            withContext(Dispatchers.Main) {
                view.setFavorite()
            }
        }
    }

}

interface FinderDetailView {

    fun setViewValues(
        general: MovieDetail,
        cast_crew: MovieDetail
    )

    fun setFavorite()

}
