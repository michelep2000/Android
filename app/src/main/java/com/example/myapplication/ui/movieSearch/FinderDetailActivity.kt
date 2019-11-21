package com.example.myapplication.ui.movieSearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.model.RetrofitFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_finder_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File.separator

class FinderDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_finder_detail)

        val id = intent.extras?.getInt("id")

        val service = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getMovieDetail(id)
            val response2 = service.getMovieCastAndCrew(id)
            Log.e("response detail", response.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val attribute = response.body()!!
                    val attribute2 = response2.body()!!
                    movieTitle.text = attribute.title
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/" + attribute.backdrop_path)
                        .into(movieImg)
                    txtYearValue.text = attribute.release_date
                    txtRate.text = "${attribute.vote_average}"
                    txtDescriptionMovie.text = attribute.overview
                    txtGenreValue.text = attribute.genres.joinToString(separator = ", "){ it.name }
                    txtCastValue.text = "${attribute2.cast[0].name},${attribute2.cast[1].name},${attribute2.cast[2].name}"
                    txtDirectorValue.text = attribute2.crew.filter{it.job == "Director" }.map { it.name }.joinToString (separator = ", ")
                }
            }
        }
    }


}
