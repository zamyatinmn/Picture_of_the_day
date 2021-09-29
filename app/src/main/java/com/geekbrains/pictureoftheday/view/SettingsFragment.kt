package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.Tools
import com.geekbrains.pictureoftheday.databinding.FragmentMarsBinding
import com.geekbrains.pictureoftheday.databinding.FragmentSettingsBinding


/**
 * Created by Maxim Zamyatin on 12.09.2021
 */


class SettingsFragment :  ViewBindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTheme()
        ui.themesBtn.setOnClickListener{
            TransitionManager.beginDelayedTransition(ui.themes)
            val params: ViewGroup.LayoutParams = ui.themesBtn.layoutParams
            params.width = ui.root.width
            params.height = 20
            ui.themesBtn.layoutParams = params
            ui.moon.visibility = View.VISIBLE
            ui.mars.visibility = View.VISIBLE
            ui.milkyWay.visibility = View.VISIBLE
        }
    }

    private fun changeTheme() {
        ui.themes.check(Tools.getTheme(requireActivity()))
        ui.themes.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                ui.moon.id -> activity?.setTheme(R.style.Theme_PictureOfTheDay_Moon)
                ui.mars.id -> activity?.setTheme(R.style.Theme_PictureOfTheDay_Mars)
                ui.milkyWay.id -> activity?.setTheme(R.style.Theme_PictureOfTheDay_MilkyWay)
            }
            Tools.putTheme(requireActivity(), checkedId)
            activity?.recreate()
        }
    }
}