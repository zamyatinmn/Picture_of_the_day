package com.geekbrains.pictureoftheday.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.Tools
import com.geekbrains.pictureoftheday.databinding.FragmentSettingsBinding
import com.google.android.material.chip.ChipGroup


/**
 * Created by Maxim Zamyatin on 12.09.2021
 */


class SettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SettingsFragment()
    }

    private var _ui: FragmentSettingsBinding? = null
    private val ui: FragmentSettingsBinding
        get() = _ui!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = FragmentSettingsBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTheme()
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
//            Toast.makeText(requireContext(), "$checkedId", Toast.LENGTH_SHORT).show()
            activity?.recreate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}