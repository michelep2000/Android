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
import com.example.myapplication.model.Movie
import com.example.myapplication.ui.search.detail.FinderDetailActivity


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
        presenter = FinderPresenter(this)

        viewAdapter = MoviesAdapter {
            presenter.onMovieDetailClicked(it.id)
        }

        view.finderRecyclerView.adapter = viewAdapter
        view.finderSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                presenter.onSearch(text)
                return false
            }

            override fun onQueryTextSubmit(text: String): Boolean {
                return false
            }

        })

        view.finderRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.finderRecyclerView.setHasFixedSize(true)

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

