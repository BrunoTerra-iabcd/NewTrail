package com.iabcd.newtrail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class RocketViewModel(
    initialRocketX: Int,
    initialRocketY: Int
) : ViewModel() {

    private var isBinded = false
    var isBeingScrolled = false

    private val _rocketCoordinates: MutableStateFlow<IntArray> =
        MutableStateFlow(intArrayOf(initialRocketX, initialRocketY))
     val rocketCoordinates: Flow<IntArray> = _rocketCoordinates

    fun updateRocketPositionFromCLick(coordinates: IntArray){
        isBinded = true
        _rocketCoordinates.value = coordinates
    }

    fun updateRocketPositionOnScroll(offSetX: Int, offSetY: Int){
        val current = _rocketCoordinates.value
        val updatedArray = intArrayOf(current[0] - offSetX,current[1] - offSetY)
        _rocketCoordinates.value = updatedArray
    }

    fun isBinded() = isBinded

}

class RocketViewModelFactory(private val rocketX: Int, private val rocketY: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RocketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RocketViewModel(rocketX, rocketY) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}