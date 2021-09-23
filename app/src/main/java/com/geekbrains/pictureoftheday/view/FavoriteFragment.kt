package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.databinding.FragmentFavoriteBinding
import com.geekbrains.pictureoftheday.databinding.FragmentPictureBinding


/**
 * Created by Maxim Zamyatin on 12.09.2021
 */


class FavoriteFragment : ViewBindingFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {
    companion object {
        fun newInstance() = FavoriteFragment()
    }
}