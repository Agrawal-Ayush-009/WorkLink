package com.example.worklink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapterMain(fm : FragmentActivity): FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return AllFragment()
            1 -> return RecommendedFragment()
            2 -> return PopularFragment()
            3 -> return NearYouFragment() // Add additional fragment for tab 4
            else -> return AllFragment()
        }
    }
}