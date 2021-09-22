package com.geekbrains.pictureoftheday

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout


/**
 * Created by Maxim Zamyatin on 22.09.2021
 */


class CustomBehavior(context: Context? = null, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<View>(context, attrs) {
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ) = dependency is LinearLayout

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        child.layoutParams.width = dependency.top
        child.requestLayout()
        return false
    }
}