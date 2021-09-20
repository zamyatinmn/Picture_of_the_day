package com.geekbrains.pictureoftheday.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geekbrains.pictureoftheday.*


/**
 * Created by Maxim Zamyatin on 19.09.2021
 */


class PagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {


    private val fragments = arrayOf(
        MainFragment.newInstance(DAY_BEFORE_YESTERDAY_MODE),
        MainFragment.newInstance(YESTERDAY_MODE),
        MainFragment.newInstance()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            DAY_BEFORE_YESTERDAY_INDEX -> fragments[DAY_BEFORE_YESTERDAY_INDEX]
            YESTERDAY_INDEX -> fragments[YESTERDAY_INDEX]
            TODAY_INDEX -> fragments[TODAY_INDEX]
            else -> fragments[DAY_BEFORE_YESTERDAY_INDEX]
        }
    }

}