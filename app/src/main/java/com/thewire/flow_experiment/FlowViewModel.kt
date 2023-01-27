package com.thewire.flow_experiment

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FlowViewModel() : ViewModel() {
    private val backend = Backend()
    val data = mutableStateOf("")
    val flowFlowData = mutableStateListOf<String>()
    val errorState = mutableStateOf(false)
    val loadingState = mutableStateOf(false)
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

    fun getFlowFlow() {
        viewModelScope.launch {
            backend.getFlowFlow().collect {
                loadingState.value = it.loading
                it.data?.let { text ->
                    flowFlowData.add(text)
                    println(text)
                }
                it.error?.let { error ->
                    errorState.value = true
                    println("erro")
                }
            }
        }
    }
}