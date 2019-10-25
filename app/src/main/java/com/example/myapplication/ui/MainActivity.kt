package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sacando el dato directamente del xml de idiomas
        val message = getString(R.string.youClickMe)
        var count = 0

        btnClickMe.setOnClickListener {
            txtHello.text = "U-Tad"
            incrementText(count++, counter)
        }

        btnProfile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("description", R.string.description)
            intent.putExtra("city", "Madrid")
            intent.putExtra("birthdate", "28/09/2000")
            intent.putExtra("image", R.drawable.boy)
            startActivity(intent)
        }

        btnMovie.setOnClickListener {
            val intent = Intent(this, MovieDetailActivity::class.java)
            startActivity(intent)
        }
        btnMovieList.setOnClickListener {
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)
        }
    }


    fun incrementText(count: Int, counter: TextView) {
        counter.text = count.toString()
    }
}






