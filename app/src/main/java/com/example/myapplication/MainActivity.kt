package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.time.microseconds
import kotlin.time.milliseconds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sacando el id directamente del xml de activity_main
        val txtHello: TextView = findViewById(R.id.txtHello)
        val btnClickMe: Button = findViewById(R.id.btnClickMe)
        val counter: TextView = findViewById(R.id.counter)

        //sacando el dato directamente del xml de idiomas
        val message = getString(R.string.youClickMe)
        var count = 0;

        btnClickMe.setOnClickListener {

            count++
            txtHello.text = "U-Tad"
            Toast.makeText(this, message+ count.toString()+ " times",Toast.LENGTH_SHORT).show()
            counter.text = count.toString()
        }


    }
}






