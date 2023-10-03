package com.example.androidtraining

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private  val TAG = "MainViewModel"
    val time = mutableStateOf(10)

    //    Normal Flow
    val downCounter = flow {
        val startValue = 10
        var currentValue = startValue

        emit(currentValue)

        while (currentValue > 0) {
            delay(1000L)
            currentValue--

            emit(currentValue)

        }
    }

    init {
//        collectTime()
        multiNumber(10)
        viewModelScope.launch {
            _sharedFlow.collect {
                delay(2000L)
                println()
                Log.e(TAG, "Flow: First Value $it" )
            }
        }
        viewModelScope.launch {
            _sharedFlow.collect {
                delay(3000L)
                Log.e(TAG, "Flow: Second Value $it", )
            }
        }

    }

    private val _stateFlow = MutableStateFlow(0)

    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>()


    fun multiNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number)
        }
    }


    fun incrementCounter() {
        _stateFlow.value++
    }

    fun collectTime() {
//        val flow = (1..5).asFlow()
//        viewModelScope.launch {
//            flow.flatMapConcat { value ->
//                flow {
//
//                }
//
//            }
//            val count = downCounter
//                .filter { time -> time % 2 == 0 }
//                .map { time ->
//                    time * time
//                }.count {
//                    it % 2 == 0
//                }
//            println("The count is: $count")

    }
}
