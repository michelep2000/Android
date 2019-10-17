package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        print("paso contentView")


        movie = Movie(
            img = "https://i.imgur.com/T0YDgh5.png",
            title = "Zombieland 2",
            year = "2019",
            genre = "Película de zombies/Acción",
            description = "Los cazadores de zombis viajan desde la Casa Blanca hasta el corazón de los Estados Unidos, donde tendrán que defenderse de nuevas clases de muertos vivientes que han evolucionado. Mientras intentan salvar el mundo, los miembros de la pandilla también tendrán que aprender a convivir"
        )

        print("paso movie")
        with(movie) {
            Picasso.get().load(img).into(movieImg)
            print("paso picasso")
            movieTitle.text = title
            print("paso title")
            txtYearValue.text = year
            print("paso year")
            txtGenreValue.text = genre
            print("paso genre")
            txtDescriptionMovie.text = description
        }



    }


}
