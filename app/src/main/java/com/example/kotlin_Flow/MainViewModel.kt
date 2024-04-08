package com.example.kotlin_Flow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    var timer by mutableIntStateOf(0)
        private set


    // cold flow - only work when there is any subscriber
    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }


    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {

            //collect - collect all and collectLatest - collect only the latest value
            countDownFlow.collectLatest {
                timer = it
            }
        }
    }
}