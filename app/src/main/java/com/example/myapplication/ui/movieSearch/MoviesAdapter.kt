package com.example.myapplication.ui.movieSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Movie2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val listener: (Movie2) -> Unit) :
    RecyclerView.Adapter<MovieListViewHolder>() {

    private var movies = listOf<Movie2>()

    fun addMovies(newMovies: List<Movie2>) {
        this.movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.from(parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }
}

class MovieListViewHolder private constructor(val view: ConstraintLayout) :
    RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): MovieListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            return MovieListViewHolder(view as ConstraintLayout)
        }
    }

    fun bind(movieItem: Movie2, listener: (Movie2) -> Unit) {


        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movieItem.poster_path).into(view.movieListImg)
        view.movieListTitle.text = movieItem.title
        view.movieListOriginalTitle.text = movieItem.original_title
        view.movieListReleaseDateValue.text = movieItem.release_date
        view.movieListRate.text = "${movieItem.vote_average}"
        this.itemView.setOnClickListener { listener.invoke(movieItem) }

    }

}