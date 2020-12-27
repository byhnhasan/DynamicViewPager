package com.hsnbyhn.dynamicviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by hasanbayhan on 27.12.2020
 **/
class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FirstFragment.newInstance()
            1 -> SecondFragment.newInstance()
            else -> throw IllegalStateException("no more tab")
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "First"
            1 -> "Second"
            else -> ""
        }
    }
}