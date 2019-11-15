package com.example.myapplication.ui.search.detail

import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.model.MovieDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FinderDetailPresenter(
    private val view: FinderDetailView,
    private val remoteRepository: RemoteRepository
) {

    fun onLoadDetail(id: Int?) {
        CoroutineScope(Dispatchers.IO).launch {
            val responseGeneral = remoteRepository.getMovieDetail(id)
            val responseCastCrew = remoteRepository.getMovieCastAndCrew(id)
            withContext(Dispatchers.Main) {
                if (responseGeneral != null && responseCastCrew != null) {
                    view.setViewValues(responseGeneral, responseCastCrew)
                }
            }
        }
    }

}

interface FinderDetailView {
    fun setViewValues(
        general: MovieDetail,
        cast_crew: MovieDetail
    )

}
