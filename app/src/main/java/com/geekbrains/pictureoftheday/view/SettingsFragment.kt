package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.databinding.FragmentSettingsBinding


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

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}