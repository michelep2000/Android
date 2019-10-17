package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var txtHello: TextView
    private lateinit var btnClickMe: Button
    private lateinit var counter: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnMovie: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sacando el id directamente del xml de activity_main
        txtHello = findViewById(R.id.txtHello)
        btnClickMe = findViewById(R.id.btnClickMe)
        counter = findViewById(R.id.counter)
        btnProfile = findViewById(R.id.btnProfile)
        btnMovie = findViewById(R.id.btnMovie)

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
    }


    fun incrementText(count: Int, counter: TextView) {
        counter.text = count.toString()
    }
}






