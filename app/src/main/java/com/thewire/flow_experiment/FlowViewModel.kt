package com.thewire.flow_experiment

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val TAG = "FLOW_VIEWMODEL"
    class FlowViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val backend = Backend()
    private val intermediate = Intermediate(backend)
    val data = mutableStateOf("")
    val flowFlowData = mutableStateListOf<String>()
    val filteredFlowFlowData = mutableStateListOf<String>()
    val errorState = mutableStateOf(false)
    val loadingState = mutableStateOf(false)
    val flowData = backend.anotherFlow()
    var anotherFlow = flowOf<String?>(null)

    val dataToSave = savedStateHandle.getStateFlow(key ="dataToSave", initialValue = "").map {
        Log.i(TAG, it)
        it
    }

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

    fun saveData(data: String) {
        savedStateHandle.set(key="dataToSave", data)
    }

    fun getFilteredFlowFlow() {
        viewModelScope.launch {
            intermediate.getFilteredFlowFlow.collect {
                loadingState.value = it.loading
                it.data?.let { text ->
                    filteredFlowFlowData.add(text)
                    println(text)
                }
                it.error?.let { error ->
                    errorState.value = true
                    println("error $error")
                }
            }
        }
    }

    fun startAnotherFLow() {
        // this approach doesn't work on its own doesn't recompose
        Log.i(TAG, "startAnotherFLow")
        anotherFlow = backend.anotherFlow()
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
                    println("error $error")
                }
            }
        }
    }
}