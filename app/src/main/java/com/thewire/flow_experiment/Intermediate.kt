package com.thewire.flow_experiment

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot

class Intermediate(backend: Backend) {

    val getFilteredFlowFlow: Flow<DataState<String>> =
        backend.getFlowFlow()
            .filterNot { data ->
                0 < (data.data?.let {
                    return@let it.split(" ").getOrNull(4)?.toInt()
                } ?: 2) % 2
            }
}