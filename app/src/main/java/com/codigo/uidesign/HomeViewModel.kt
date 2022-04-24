package com.codigo.uidesign

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigo.uidesign.carousel.CarouselAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel(),DefaultLifecycleObserver {

    private var _currentItem = MutableStateFlow(0)
    val currentItem = _currentItem.asStateFlow()

    private var job : Job? = null

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (job != null && job!!.isActive) job!!.cancel()
        updateCurrentItemEvery5Sec()
    }

    private fun updateCurrentItemEvery5Sec() {
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(5000)
                if (currentItem.value < 3)
                    _currentItem.value += 1
                else
                    _currentItem.value = 0
            }
        }
    }
}