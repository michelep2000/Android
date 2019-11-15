package com.example.myapplication.ui.search.search

import com.example.myapplication.data.repository.remote.RetrofitFactory
import com.example.myapplication.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FinderPresenter(val view: FinderView) {

    fun onSearch(term: String) {
        val service = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.searchMovies(term)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    view.addMovies(response.body()!!.results)
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