package com.geekbrains.pictureoftheday

import android.app.Activity
import android.content.Context


/**
 * Created by Maxim Zamyatin on 15.09.2021
 */


class Tools {
    companion object {
        private const val PREFS_KEY = "prefs"
        private const val THEME_KEY = "theme"

        fun getThemeId(activity: Activity): Int{
            return when(getTheme(activity)){
                R.id.moon -> R.style.Theme_PictureOfTheDay_Moon
                R.id.mars -> R.style.Theme_PictureOfTheDay_Mars
                R.id.milkyWay -> R.style.Theme_PictureOfTheDay_MilkyWay
                else -> R.style.Theme_PictureOfTheDay_Moon
            }
        }

        fun getTheme(activity: Activity): Int {
            return activity.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
                .getInt(THEME_KEY, R.id.moon)
        }

        fun putTheme(activity: Activity, themeId: Int) {
            activity.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
                .edit().putInt(THEME_KEY, themeId).apply()
        }
    }
}