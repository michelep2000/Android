package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var txtCity: TextView
    private lateinit var txtBirthday: TextView
    private lateinit var  txtDescription: TextView
    private lateinit var myImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        txtBirthday = findViewById(R.id.txtBirthdate)
        txtCity = findViewById(R.id.txtCity)
        txtDescription = findViewById(R.id.txtDescription)
        myImageView = findViewById(R.id.imageView)

        val city = intent.extras?.getString("city")
        val birthdate = intent.extras?.getString("birthdate")
        val description = intent.extras?.getInt("description")
        val image  = intent.extras?.getInt("image")

        txtCity.text = city
        txtBirthday.text = birthdate
        description?.let { txtDescription.setText(description)} ?: return
        image?.let { myImageView.setImageResource(image)} ?: return
    }
}
