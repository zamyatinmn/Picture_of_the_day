package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.View
import com.geekbrains.pictureoftheday.DAY_BEFORE_YESTERDAY_INDEX
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.TODAY_INDEX
import com.geekbrains.pictureoftheday.YESTERDAY_INDEX
import com.geekbrains.pictureoftheday.databinding.FragmentApodBinding
import com.google.android.material.tabs.TabLayoutMediator


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


class ApodFragment : ViewBindingFragment<FragmentApodBinding>(FragmentApodBinding::inflate) {
    companion object {
        fun newInstance() = ApodFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.viewPager.adapter = PagerAdapter(requireActivity())
        ui.viewPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(ui.tabLayout, ui.viewPager) { tab, position ->
            when (position) {
                DAY_BEFORE_YESTERDAY_INDEX -> tab.text = getString(R.string.day_before_yesterday)
                YESTERDAY_INDEX -> tab.text = getString(R.string.yesterday)
                TODAY_INDEX -> tab.text = getString(R.string.today)
            }
        }.attach()

        ui.tabLayout.setScrollPosition(TODAY_INDEX, 0f, true)
        ui.viewPager.currentItem = TODAY_INDEX
    }
}