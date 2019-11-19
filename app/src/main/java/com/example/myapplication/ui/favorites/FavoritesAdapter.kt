package com.example.myapplication.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.repository.local.Favorites
import com.example.myapplication.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class FavoritesAdapter(private val listener: (Favorites) -> Unit) :
    RecyclerView.Adapter<FavoriteListViewHolder>() {

    private var favorites = listOf<Favorites>()

    fun addFavorites(favorites: List<Favorites>) {
        this.favorites = favorites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder.from(parent)
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bind(favorites[position], listener)
    }
}

class FavoriteListViewHolder private constructor(val view: ConstraintLayout) :
    RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): FavoriteListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            return FavoriteListViewHolder(view as ConstraintLayout)
        }
    }

    fun bind(movieItem: Favorites, listener: (Favorites) -> Unit) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieItem.poster_path)
            .into(view.movieListImg)
        view.movieListTitle.text = movieItem.title
        view.movieListOriginalTitle.text = movieItem.original_title
        view.movieListReleaseDateValue.text = movieItem.release_date
        view.movieListRate.text = "${movieItem.vote_average}"
        this.itemView.setOnClickListener { listener.invoke(movieItem) }


    }

}