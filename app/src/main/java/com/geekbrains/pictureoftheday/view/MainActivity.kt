package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.Tools
import com.geekbrains.pictureoftheday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Tools.getThemeId(this))
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setBottomNavigation()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(ui.container.id, ApodFragment.newInstance())
                .commit()
        }

    }

    private fun setBottomNavigation() {
        ui.bottomNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    changeScreen(ApodFragment.newInstance())
                    true
                }
                R.id.menu_epic -> {
                    changeScreen(EpicFragment.newInstance())
                    true
                }
                R.id.menu_mars -> {
                    changeScreen(MarsFragment.newInstance())
                    true
                }
                R.id.menu_settings -> {
                    changeScreen(SettingsFragment.newInstance())
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