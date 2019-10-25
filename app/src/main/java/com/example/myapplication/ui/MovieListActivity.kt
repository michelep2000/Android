package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Movie2

class MovieListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MoviesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var myMovieList : List<Movie2>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)


        myMovieList = listOf(
            Movie2(
                img = "https://vignette.wikia.nocookie.net/doblaje/images/9/99/Zombieland_2_Poster_La.png/revision/latest?cb=20190909163524&path-prefix=es",
                title = "Tierra de zombies",
                originalTitle = "Zombieland",
                releaseDate = "27/04/1803",
                rate = "9.5"
            ),
            Movie2(
                img = "https://occ-0-1722-92.1.nflxso.net/dnm/api/v6/0DW6CdE4gYtYx8iy3aj8gs9WtXE/AAAABfaeGrwmqqVK8XSJ3XREsbvWvMYnxwks5DcFR6lyc2Hh536_XlO6xGBcv5grXddfUjfMY0etjcp45wworQ3GAnsFHAM.jpg?r=80f",
                title = "Mamá",
                originalTitle = "Mama",
                releaseDate = "15/01/1995",
                rate = "8.7"
            ),
            Movie2(
                img = "https://d500.epimg.net/cincodias/imagenes/2018/06/29/smarttv/1530279996_778826_1530280220_noticia_normal.jpg",
                title = "Jumanji",
                originalTitle = "Jumanji",
                releaseDate = "18/08/1999",
                rate = "6.3"
            ),
            Movie2(
                img = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/capitana-marvel-poster-1551691489.jpg?crop=1xw:1xh;center,top&resize=480:*",
                title = "Capitana Marvel",
                originalTitle = "Captain Marvel",
                releaseDate = "28/09/2000",
                rate = "5.0"
            ),
            Movie2(
                img = "https://statics-uestudio.uecdn.es/buhomag/2019/06/aladdin-accion-real-disney.jpg",
                title = "Aladdín",
                originalTitle = "Aladdin",
                releaseDate = "20/07/1765",
                rate = "3.2"
            ),
            Movie2(
                img = "https://as01.epimg.net/meristation/imagenes/2019/01/03/noticias/1546514335_674507_1546514391_noticia_normal.jpg",
                title = "Vengadores: End Game",
                originalTitle = "Avengers: End Game",
                releaseDate = "12/05/1600",
                rate = "10.0"

            )

        )
        viewManager = LinearLayoutManager(this)
        viewAdapter = MoviesAdapter(myMovieList)

        recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
}
