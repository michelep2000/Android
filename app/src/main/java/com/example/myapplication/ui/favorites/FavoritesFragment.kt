package com.example.myapplication.ui.favorites


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.repository.local.FavoriteMoviesLocalRepository
import com.example.myapplication.data.repository.local.Favorites
import com.example.myapplication.data.repository.local.FavoritesFactory
import com.example.myapplication.data.repository.local.LocalRepository
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import android.view.*
import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.data.repository.remote.RetrofitFactory
import com.example.myapplication.data.repository.remote.RetrofitRemoteRepository
import com.example.myapplication.ui.search.detail.FinderDetailActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment(), FavoritesView {

    private lateinit var viewAdapter: FavoritesAdapter
    private lateinit var presenter: FavoritesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        setHasOptionsMenu(true)

        val localRepository: LocalRepository =
            FavoriteMoviesLocalRepository(FavoritesFactory.get(activity!!))

        presenter = FavoritesPresenter(this, localRepository)

        viewAdapter = FavoritesAdapter {
            presenter.onMovieClicked(it.id)
        }
        view.favoritesRecyclerView.adapter = viewAdapter
        presenter.onLoad()
        view.favoritesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.favoritesRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.fav_items, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun setAdapter(favorites: List<Favorites>) {
        viewAdapter.addFavorites(favorites)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.item_obTitle -> {
                presenter.orderByTitleClicked()
                return true
            }
            R.id.item_obDate -> {
                presenter.orderByDateClicked()
                return true
            }
            R.id.item_delete -> {
                presenter.deleteClicked()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openDetail(id: Int) {
        val intent = Intent(this.context, FinderDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onResume() {
        presenter.onLoad()
        super.onResume()
    }
}
