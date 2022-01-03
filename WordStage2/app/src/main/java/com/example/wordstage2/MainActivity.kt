package com.example.wordstage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wordstage2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var activityBinding : ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController = navHostFragment.navController

        //This ensures action bar (app bar) buttons, like the menu option in LetterListFragment are visible.
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}