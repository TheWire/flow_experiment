package com.thewire.flow_experiment

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Backend() {

    fun getFlow(): Flow<DataState<String>> = flow {
        emit(DataState.loading())
        emit(DataState.success("this is flow"))
        emit(DataState.success("more flow"))
    }
}