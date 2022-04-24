package com.codigo.uidesign

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.codigo.uidesign.adapter.RateAdapter
import com.codigo.uidesign.adapter.RoomAdapter
import com.codigo.uidesign.carousel.CarouselAdapter
import com.codigo.uidesign.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val items = listOf(R.drawable.beach_one,R.drawable.beach_two,R.drawable.beach_three)

    private lateinit var carouselAdapterTwo: CarouselAdapter
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var rateAdapter: RateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        lifecycle.addObserver(viewModel)
        initialize()
    }

    override fun onStart() {
        super.onStart()
        infoAnimation()
        lifecycleScope.launchWhenStarted {
            viewModel
                .currentItem
                .flowWithLifecycle(lifecycle,Lifecycle.State.STARTED)
                .collectLatest { binding.carouselViewPager.currentItem = it }
        }
    }

    private fun infoAnimation() {
        val animation = AnimatorInflater.loadAnimator(requireContext(),R.animator.info_animator) as ValueAnimator
        animation.apply {
            addUpdateListener {
                val animationValue = it.animatedValue as Float
                binding.carouselViewPager.apply {
                    alpha = animationValue
                    scaleX = animationValue
                    scaleY = animationValue
                }

                binding.infoOne.apply {
                    alpha = animationValue
                    scaleX = animationValue
                    scaleY = animationValue
                }
                binding.infoOneTxt.alpha = animationValue
                binding.infoTwo.apply {
                    alpha = animationValue
                    scaleX = animationValue
                    scaleY = animationValue
                }
                binding.infoTwoTxt.alpha = animationValue
                binding.infoThree.apply {
                    alpha = animationValue
                    scaleX = animationValue
                    scaleY = animationValue
                }
                binding.infoThreeTxt.alpha = animationValue
                binding.infoFive.apply {
                    alpha = animationValue
                    scaleX = animationValue
                    scaleY = animationValue
                }
                binding.infoFourTxt.alpha = animationValue
                binding.infoFive.apply {
                    alpha = animationValue
                    scaleX = animationValue
                    scaleY = animationValue
                }
                binding.infoFiveTxt.alpha = animationValue

                binding.bottomRecycler.alpha = animationValue
            }
            start()
        }
    }

    private fun initialize() {
        carouselAdapterTwo = CarouselAdapter(childFragmentManager,lifecycle)
        binding.carouselViewPager.adapter = carouselAdapterTwo
        carouselAdapterTwo.submitData(items)

        roomAdapter = RoomAdapter()
        rateAdapter = RateAdapter()
        binding.bottomRecycler.adapter = roomAdapter

        binding.carouselViewPager.registerOnPageChangeCallback(pageChangeCallback)

        binding.roomBtn.setOnClickListener(this)
        binding.rateBtn.setOnClickListener(this)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.roomBtn -> {
                binding.roomBtn.setBackgroundColor(requireContext().getColor(R.color.tab_border))
                binding.rateBtn.background = requireContext().resources.getDrawable(R.drawable.tab_background,requireContext().theme)
                binding.bottomRecycler.adapter = roomAdapter
            }
            R.id.rateBtn -> {
                binding.rateBtn.setBackgroundColor(requireContext().getColor(R.color.tab_border))
                binding.roomBtn.background = requireContext().resources.getDrawable(R.drawable.tab_background,requireContext().theme)
                binding.bottomRecycler.adapter = rateAdapter
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        carouselAdapterTwo.submitData(items)
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            binding.carouselTxt.text = "See All ${position+1}/3"
        }
    }
}