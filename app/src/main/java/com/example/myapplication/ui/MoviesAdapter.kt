package com.example.myapplication.ui
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Movie2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter (val movieList: List<Movie2>) : RecyclerView.Adapter<MovieListViewHolder>(){
    override fun getItemCount(): Int = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
       return MovieListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movieItem = movieList[position]
        holder.bind(movieItem)
    }
}

class MovieListViewHolder private constructor(val view: ConstraintLayout): RecyclerView.ViewHolder(view){
    companion object{
        fun from(parent: ViewGroup): MovieListViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            return MovieListViewHolder(view as ConstraintLayout)
        }
    }

    fun bind(movieItem: Movie2){
        Picasso.get().load(movieItem.img).into(view.movieListImg)
        view.movieListTitle.text = movieItem.title
        view.movieListOriginalTitle.text = movieItem.originalTitle
        view.movieListReleaseDateValue.text = movieItem.releaseDate
        view.movieListRate.text = movieItem.rate
    }

}