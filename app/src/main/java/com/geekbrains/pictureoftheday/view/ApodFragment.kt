package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.pictureoftheday.DAY_BEFORE_YESTERDAY_INDEX
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.TODAY_INDEX
import com.geekbrains.pictureoftheday.YESTERDAY_INDEX
import com.geekbrains.pictureoftheday.databinding.FragmentApodBinding
import com.geekbrains.pictureoftheday.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


class ApodFragment : Fragment() {
    companion object {
        fun newInstance() = ApodFragment()
    }

    private var _ui: FragmentApodBinding? = null
    private val ui: FragmentApodBinding
        get() = _ui!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = FragmentApodBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.viewPager.adapter = PagerAdapter(requireActivity())
        ui.viewPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(ui.tabLayout, ui.viewPager) { tab, position ->
            when(position){
                DAY_BEFORE_YESTERDAY_INDEX -> tab.text = getString(R.string.day_before_yesterday)
                YESTERDAY_INDEX -> tab.text = getString(R.string.yesterday)
                TODAY_INDEX -> tab.text = getString(R.string.today)
            }
        }.attach()

        ui.tabLayout.setScrollPosition(TODAY_INDEX, 0f, true)
        ui.viewPager.currentItem = TODAY_INDEX
    }

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}