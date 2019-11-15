package com.example.myapplication.ui.search.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.repository.remote.RemoteRepository
import com.example.myapplication.data.repository.remote.RetrofitFactory
import com.example.myapplication.data.repository.remote.RetrofitRemoteRepository
import com.example.myapplication.model.MovieDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_finder_detail.*


class FinderDetailActivity : AppCompatActivity(), FinderDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_finder_detail)

        val remoteRepository: RemoteRepository =
            RetrofitRemoteRepository(RetrofitFactory.getMovieApi())
        val presenter = FinderDetailPresenter(this, remoteRepository)

        val id = intent.extras?.getInt("id")
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
        txtCastValue.text =
            "${cast_crew.cast[0].name},${cast_crew.cast[1].name},${cast_crew.cast[2].name}"
        txtDirectorValue.text = cast_crew.crew.filter { it.job == "Director" }.map { it.name }
            .joinToString(separator = ", ")
    }

}
