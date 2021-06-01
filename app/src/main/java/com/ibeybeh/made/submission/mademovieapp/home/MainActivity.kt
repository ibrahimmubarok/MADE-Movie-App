package com.ibeybeh.made.submission.mademovieapp.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ibeybeh.made.submission.mademovieapp.R
import com.ibeybeh.made.submission.mademovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        setSupportActionBar(mainBinding.mainToolbar.toolbar)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_movies,
            R.id.navigation_tvshow,
            R.id.navigation_favorite
        ).build()

        val navView = mainBinding.navMainView
        val navController = Navigation.findNavController(this, R.id.navHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }
}