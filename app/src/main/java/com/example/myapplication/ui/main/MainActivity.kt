package com.example.myapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.find_fragment, R.id.movieList_fragment, R.id.profile_fragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }
}






