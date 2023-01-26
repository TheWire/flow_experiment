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
import androidx.compose.ui.tooling.preview.Preview
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
                    flowThing(viewModel::getFlow, viewModel.data.value)
                }
            }
        }
    }
}

@Composable
fun flowThing(callback: () -> Unit, data: String) {
    Column() {
        Button(
            onClick = callback
        ) {
            Text("Get Flow")
        }
        Text(data)
    }
}
