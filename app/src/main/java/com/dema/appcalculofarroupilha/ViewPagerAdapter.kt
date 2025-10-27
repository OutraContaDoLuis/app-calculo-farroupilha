package com.dema.appcalculofarroupilha

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragments : ArrayList<Fragment>,
    fragmentManager : FragmentManager,
    lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private var fragments : ArrayList<Fragment> = ArrayList()

    init {
        this.fragments = fragments
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}