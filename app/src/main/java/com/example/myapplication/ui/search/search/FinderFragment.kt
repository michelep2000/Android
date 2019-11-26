package com.example.myapplication.ui.search.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_finder.view.*
import android.content.Intent
import androidx.core.view.isVisible
import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.data.repository.remote.RetrofitFactory
import com.example.myapplication.data.repository.remote.RetrofitRemoteRepository
import com.example.myapplication.model.Movie
import com.example.myapplication.ui.search.detail.FinderDetailActivity
import kotlinx.android.synthetic.main.activity_finder_detail.*


/**
 * A simple [Fragment] subclass.
 */
class FinderFragment : Fragment(), FinderView {

    private lateinit var viewAdapter: MoviesAdapter
    private lateinit var presenter: FinderPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_finder, container, false)
        val remoteRepository: RemoteRepository =
            RetrofitRemoteRepository(RetrofitFactory.getMovieApi())
        presenter = FinderPresenter(this, remoteRepository)

        viewAdapter = MoviesAdapter {
            presenter.onMovieDetailClicked(it.id)
        }

        view.favoritesRecyclerView.adapter = viewAdapter
        view.finderSearch.isIconifiedByDefault = false
        view.finderSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                presenter.onSearch(text)
                return false
            }

            override fun onQueryTextSubmit(text: String): Boolean {
                return false
            }

        })

        view.favoritesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.favoritesRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun openMovieDetail(id: Int) {
        val intent = Intent(this.context, FinderDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun addMovies(newMovies: List<Movie>) {
        viewAdapter.addMovies(newMovies)
    }
}

