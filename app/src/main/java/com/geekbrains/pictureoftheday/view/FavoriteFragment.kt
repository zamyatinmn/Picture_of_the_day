package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.databinding.FragmentFavoriteBinding


/**
 * Created by Maxim Zamyatin on 12.09.2021
 */


class FavoriteFragment : Fragment() {
    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var _ui: FragmentFavoriteBinding? = null
    private val ui: FragmentFavoriteBinding
        get() = _ui!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = FragmentFavoriteBinding.inflate(inflater)
        return ui.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}