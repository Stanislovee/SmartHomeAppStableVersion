package com.example.smarthome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smarthome.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_temperature_settings,
                R.id.navigation_add_device,
                R.id.navigation_device_manager,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val hiddenDestinations = setOf(
                R.id.navigation_notifications,
                R.id.navigation_settings,
                R.id.authFragment,
                R.id.navigation_help
            )

            if (destination.id in hiddenDestinations) {
                hideBottomNavigation()
            } else {
                showBottomNavigation()
            }
        }


        navView.visibility = android.view.View.GONE
    }


    fun showBottomNavigation() {
        binding.navView.visibility = android.view.View.VISIBLE
    }


    fun hideBottomNavigation() {
        binding.navView.visibility = android.view.View.GONE
    }
}