package com.codigo.uidesign.carousel

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CarouselAdapter(
    fm : FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm,lifecycle) {

    private var items = mutableListOf<Int>()

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = CarouselItemFragment(imageId = items[position])

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list : List<Int>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}