package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.Tools
import com.geekbrains.pictureoftheday.databinding.ActivityMainBinding
import com.geekbrains.pictureoftheday.view.recycler.RecyclerFragment

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Tools.getThemeId(this))
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setBottomNavigation()
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
                R.id.menu_rec -> {
                    changeScreen(RecyclerFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun changeScreen(fragment: Fragment) {
        if (ui.container.visibility == View.GONE) activateFragmentContainer()
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(ui.container.id, fragment).commit()
    }

    private fun activateFragmentContainer(){
        ui.container.visibility = View.VISIBLE
        ui.hello.visibility = View.GONE
    }
}