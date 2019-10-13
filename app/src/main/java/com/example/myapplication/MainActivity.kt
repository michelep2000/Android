package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.time.microseconds
import kotlin.time.milliseconds

class MainActivity : AppCompatActivity() {

    private lateinit var txtHello: TextView
    private lateinit var btnClickMe: Button
    private lateinit var counter: TextView
    private lateinit var btnProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sacando el id directamente del xml de activity_main
        txtHello = findViewById(R.id.txtHello)
        btnClickMe = findViewById(R.id.btnClickMe)
        counter = findViewById(R.id.counter)
        btnProfile = findViewById(R.id.profile_button)

        //sacando el dato directamente del xml de idiomas
        val message = getString(R.string.youClickMe)
        var count = 0;

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
    }

    fun incrementText(count: Int, counter: TextView) {
        counter.text = count.toString()
    }
}






