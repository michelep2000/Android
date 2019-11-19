package com.example.myapplication.ui.favorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.repository.local.Favorites
import com.example.myapplication.ui.search.search.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_favorites.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment(), FavoritesView {

    private lateinit var viewAdapter: MoviesAdapter
    private lateinit var presenter: FavoritesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        viewAdapter = MoviesAdapter {
            presenter.onLoad()
        }
        view.favoritesRecyclerView.adapter = viewAdapter

        view.favoritesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.favoritesRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun setAdapter(favorites: List<Favorites>) {
        viewAdapter.addFavorites(favorites)

    }


}
