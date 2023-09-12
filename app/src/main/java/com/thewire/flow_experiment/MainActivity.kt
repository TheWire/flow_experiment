package com.thewire.flow_experiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thewire.flow_experiment.ui.theme.Flow_experimentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: FlowViewModel by viewModels()
        setContent {
            Flow_experimentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        FlowThing(viewModel::getFlow, viewModel.data.value)
                        FlowFlowThing(
                            callback = viewModel::getFlowFlow,
                            data = viewModel.flowFlowData,
                            loading = viewModel.loadingState.value,
                            error = viewModel.errorState.value,
                        )
                        FlowFlowThing(
                            callback = viewModel::getFilteredFlowFlow,
                            data = viewModel.filteredFlowFlowData,
                            loading = viewModel.loadingState.value,
                            error = viewModel.errorState.value,
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun FlowThing(callback: () -> Unit, data: String) {
    Column() {
        Button(
            onClick = callback
        ) {
            Text("Get Flow")
        }
        Text(data)
    }
}

@Composable
fun FlowFlowThing(
    callback: () -> Unit,
    data: List<String>,
    loading: Boolean,
    error: Boolean
) {
    Column() {
        Button(
            onClick = callback
        ) {
            Text("Get Flow Flow")
        }
        if (error) {
            Text("There is an error")
        } else {
            if (loading) {
                Text("Loading...")
            } else {
                for (d in data) {
                    Text(d)
                }
            }
        }

    }
}
