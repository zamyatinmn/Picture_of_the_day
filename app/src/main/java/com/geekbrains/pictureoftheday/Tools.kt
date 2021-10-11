package com.geekbrains.pictureoftheday

import android.app.Activity
import android.content.Context
import android.graphics.*


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

        fun getRoundedCornerBitmap(bitmap: Bitmap, pixels: Int): Bitmap {
            val output = Bitmap.createBitmap(
                bitmap.width, bitmap
                    .height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            val color = -0xbdbdbe
            val paint = Paint()
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rectF = RectF(rect)
            val roundPx = pixels.toFloat()
            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(bitmap, rect, rect, paint)
            return output
        }
    }
}