package com.thewire.flow_experiment

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun first_result_is_loading() {
        val backend = Backend()
        runBlocking {
            val result = backend.getFlow().first()
            assertEquals(result.loading, true)
        }
    }
}