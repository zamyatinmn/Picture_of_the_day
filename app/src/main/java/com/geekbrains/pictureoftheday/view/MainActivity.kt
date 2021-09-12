package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setBottomNavigation()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(ui.container.id, MainFragment.newInstance())
                .commit()
        }

    }

    private fun setBottomNavigation() {
        ui.bottomNavi.  setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menu_main -> {
                    changeScreen(MainFragment.newInstance())
                    true
                }
                R.id.menu_settings -> {
                    changeScreen(SettingsFragment.newInstance())
                    true
                }
                R.id.menu_favorite -> {
                    changeScreen(FavoriteFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun changeScreen(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(ui.container.id, fragment).commit()
    }
}