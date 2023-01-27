package com.thewire.flow_experiment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class Backend() {

    fun getFlow(): Flow<DataState<String>> = flow {
        emit(DataState.loading())
        emit(DataState.success("this is flow"))
        emit(DataState.success("more flow"))
        throw (Exception("flow exception"))
    }.catch {
        println("caught error")
        emit(DataState.error(it.message ?: "unknown error"))
    }

    fun getFlowFlow(): Flow<DataState<String>> = flow {
        val job = with(CoroutineScope(coroutineContext)) {
            launch {
                backGroundStuff()
            }
        }
        delay(1000)
        emit(DataState.loading())
        delay(1000)
        altFlow().collect {
            emit(it)
        }
//        anotherFlow().collect {
//            emit(DataState.success(it))
//        }
        job.cancel()
        emit(DataState.success("done"))
    }

    private fun anotherFlow(): Flow<String> = flow {
        for (i in 1..10) {
            emit("this is a string $i")
            delay(500)
        }
    }

    private fun altFlow(): Flow<DataState<String>> = flow {
        for (i in 1..10) {
            emit(DataState.success("this is a string $i"))
            delay(500)
        }
    }

    suspend fun backGroundStuff() {
        for (i in 1..10) {
            delay(1000)
            println("doing stuff in background $i")
        }
    }
}