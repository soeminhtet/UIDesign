package com.codigo.uidesign.carousel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codigo.uidesign.databinding.FragmentCarouselItemBinding


class CarouselItemFragment(private val imageId : Int? = null) : Fragment() {

    private var binding : FragmentCarouselItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarouselItemBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageId?.let {
            binding?.carouselImage?.setImageResource(imageId)
        }
    }
}