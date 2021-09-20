package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.databinding.FragmentEpicBinding


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


class EpicFragment : Fragment() {
    companion object {
        fun newInstance() = EpicFragment()
    }

    private var _ui: FragmentEpicBinding? = null
    private val ui: FragmentEpicBinding
        get() = _ui!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = FragmentEpicBinding.inflate(inflater)
        return ui.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}