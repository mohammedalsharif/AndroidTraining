package com.example.androidtraining.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtraining.ui.theme.AndroidTrainingTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Hello World What are you Ding")

        lifecycleScope.launch {
            collectLatestLifeCycle(viewModel.stateFlow) {

            }
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                //            XML
////           binding.tvCounter = viewModel.time.value.toString()
//            }

        }

        setContent {
            val viewModel: MainViewModel = viewModel()
            val time = viewModel.stateFlow.collectAsState()
            AndroidTrainingTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { viewModel.incrementCounter()

                                  },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = time.value.toString(),
                            fontSize = 30.sp,

                            )
                    }
                }

            }
        }

    }

    fun <T> ComponentActivity.collectLatestLifeCycle(flow: Flow<T>, collect: (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }

    }


}
