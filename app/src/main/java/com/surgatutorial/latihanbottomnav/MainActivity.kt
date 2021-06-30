package com.surgatutorial.latihanbottomnav

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.surgatutorial.latihanbottomnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setSupportActionBar(bind.toolbar)

        val toolbar: androidx.appcompat.widget.Toolbar = bind.toolbar
        val bottomNav: BottomNavigationView = bind.bottomNav

        val navController = findNavController(R.id.nav_host)

        val bottomNavConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_fav, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, bottomNavConfiguration)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(
                    R.id.navigation_home,
                    R.id.navigation_fav,
                    R.id.navigation_profile
                )
            ) {
                toolbar.navigationIcon = null
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.share_action) {
            Toast.makeText(this@MainActivity, "Icon Toolbar di click", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}
