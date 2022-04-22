package com.example.andyapp.presentation.quiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.properties.Delegates

class FragmentAdapter(f: Fragment) : FragmentStateAdapter(f) {

    var fragments: List<Fragment> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}