package com.example.myapplication.ui.search.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.repository.local.*
import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.data.repository.remote.RetrofitFactory
import com.example.myapplication.data.repository.remote.RetrofitRemoteRepository
import com.example.myapplication.model.MovieDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_finder_detail.*
import kotlin.properties.Delegates


class FinderDetailActivity : AppCompatActivity(), FinderDetailView {

    lateinit var presenter: FinderDetailPresenter
    var id: Int by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_finder_detail)

        val remoteRepository: RemoteRepository =
            RetrofitRemoteRepository(RetrofitFactory.getMovieApi())
        val localRepository: LocalRepository =
            FavoriteMoviesLocalRepository(FavoritesFactory.get(this))
        presenter = FinderDetailPresenter(this, remoteRepository, localRepository)

        id = intent.extras?.getInt("id")!!
        presenter.onLoadDetail(id)
    }

    override fun setViewValues(general: MovieDetail, cast_crew: MovieDetail) {
        movieTitle.text = general.title
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + general.backdrop_path)
            .into(movieImg)
        txtYearValue.text = general.release_date
        txtRate.text = "${general.vote_average}"
        txtDescriptionMovie.text = general.overview
        txtGenreValue.text = general.genres.joinToString(separator = ", ") { it.name }
        if (cast_crew.cast.isEmpty()) {
            txtCastValue.text = "Is not posible to show the cast at this time."
        } else {
            txtCastValue.text =
                "${cast_crew.cast[0].name},${cast_crew.cast[1].name},${cast_crew.cast[2].name}"
        }

        txtDirectorValue.text = cast_crew.crew.filter { it.job == "Director" }.map { it.name }
            .joinToString(separator = ", ")
    }

    override fun setFavorite(isTrue: Boolean) {
        btnFav.isEnabled = true
        if (isTrue) {
            btnFav.setImageResource(R.drawable.star_clicked)
            btnFav.setOnClickListener {
                btnFav.isEnabled = false
                presenter.disabledFavorite(id)
            }
        } else {
            btnFav.setImageResource(R.drawable.star_noclicked)
            btnFav.setOnClickListener {
                btnFav.isEnabled = false
                presenter.enableFavorite(id)
            }
        }
    }
}
