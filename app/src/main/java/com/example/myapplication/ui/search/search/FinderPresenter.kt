package com.example.myapplication.ui.search.search

import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FinderPresenter(
    private val view: FinderView,
    private val remoteRepository: RemoteRepository
) {

    fun onSearch(term: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val response = remoteRepository.searchMovies(term)
            withContext(Dispatchers.Main) {
                if (!response.results.isEmpty()) {
                    view.addMovies(response.results)
                }
            }
        }
    }

    fun onMovieDetailClicked(id: Int) {
        view.openMovieDetail(id)
    }

}
interface FinderView {
    fun addMovies(newMovies: List<Movie>)
    fun openMovieDetail(int: Int)
}