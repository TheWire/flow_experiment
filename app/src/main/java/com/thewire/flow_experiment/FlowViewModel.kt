package com.thewire.flow_experiment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class FlowViewModel() : ViewModel() {
    private val backend = Backend()
    val data = mutableStateOf("")
    fun getFlow() {
        viewModelScope.launch {
            backend.getFlow().collect {
                it.data?.let { newData ->
                    data.value = newData
                }
                println("done")
            }
        }

    }
}