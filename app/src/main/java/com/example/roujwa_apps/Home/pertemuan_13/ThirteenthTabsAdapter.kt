package com.example.roujwa_apps.Home.pertemuan_13

import TabQrcodeFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabCaptureFragment()
            1 -> TabScanFragment()
            2 -> TabQrcodeFragment()
            else -> TabCaptureFragment()
        }
    }
}